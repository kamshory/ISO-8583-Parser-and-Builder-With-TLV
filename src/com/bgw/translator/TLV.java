package com.bgw.translator;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class TLV {
	
	public TLV()
	{
		
	}
	public static Map<String, String> parse(String input)
	{
		String remaining = input;
		Map<String, String> values = new HashMap<String, String>();
		String tag = "";
		String len = "";
		String val = "";
		int itemLength = 0;
		while(remaining.length() > 0)
		{
			tag = remaining.substring(0, 2);
			len = remaining.substring(2, 4);
			try
			{
				itemLength = Integer.parseInt(len);
			}
			catch(NumberFormatException e)
			{
				itemLength = 0;
			}
			val = remaining.substring(4, 4+itemLength);
			remaining = remaining.substring(4+itemLength);
			values.put(tag, val);
		}
		return values;		
	}
	public static JSONObject parse(String data, JSONArray tags)
	{
		String remaining = data;
		JSONObject values = new JSONObject();
		String tag = "";
		String len = "";
		String val = "";
		int itemLength = 0;
		while(remaining.length() > 0)
		{
			tag = remaining.substring(0, 2);
			len = remaining.substring(2, 4);
			try
			{
				itemLength = Integer.parseInt(len);
			}
			catch(NumberFormatException e)
			{
				itemLength = 0;
			}
			val = remaining.substring(4, 4+itemLength);
			remaining = remaining.substring(4+itemLength);
			values.put(tag, val);
		}
		return values;		
	}
	public static String build(JSONObject data, JSONArray tags) 
	{
		String result = "";
		String tag = "";
		String val = "";
		int len;
		int size = tags.length();
		int i;
		JSONObject jo;
		for(i = 0; i<size; i++)
		{
			jo = tags.getJSONObject(i);
            tag = jo.optString("name", "");
            len = jo.optInt("length", 0);
            if(data.has(tag))
            {
            	val = data.optString(tag, "");
            }
            else
            {
            	val = "";
            }
            if(val.length() > len)
            {
            	val = val.substring(0, len);
            }
            result += String.format("%-2s%02d%s", tag, val.length(), val);
			
		}
		return result;
	}
}
