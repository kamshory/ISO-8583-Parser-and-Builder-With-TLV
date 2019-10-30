package test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.json.JSONException;
import org.json.JSONObject;

import com.bgw.translator.MessageTranslator;
import com.bgw.translator.RoyISO8583;
import com.bgw.translator.TLV;

public class Test {
	
	public static String config = "{\n" + 
			"    \"f41\": {\n" + 
			"        \"format\": \"%-8s\",\n" + 
			"        \"variable\": \"card_acceptor_terminal\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"8\",\n" + 
			"        \"type\": \"STRING\"\n" + 
			"    },\n" + 
			"    \"f62\": {\n" + 
			"        \"format\": \"%-200s\",\n" + 
			"        \"variable\": \"biller_message\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"200\",\n" + 
			"        \"type\": \"LLLVAR\"\n" + 
			"    },\n" + 
			"    \"f32\": {\n" + 
			"        \"format\": \"%-3s\",\n" + 
			"        \"variable\": \"acq_institution_code\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"11\",\n" + 
			"        \"type\": \"LLVAR\"\n" + 
			"    },\n" + 
			"    \"f42\": {\n" + 
			"        \"format\": \"%-15s\",\n" + 
			"        \"variable\": \"acceptor_identification_code\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"15\",\n" + 
			"        \"type\": \"STRING\"\n" + 
			"    },\n" + 
			"    \"f12\": {\n" + 
			"        \"format\": \"%-6s\",\n" + 
			"        \"variable\": \"local_time\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"6\",\n" + 
			"        \"type\": \"TIME\"\n" + 
			"    },\n" + 
			"    \"f11\": {\n" + 
			"        \"format\": \"%-6s\",\n" + 
			"        \"variable\": \"stan\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"6\",\n" + 
			"        \"type\": \"STRING\"\n" + 
			"    },\n" + 
			"    \"f13\": {\n" + 
			"        \"format\": \"%-4s\",\n" + 
			"        \"variable\": \"local_date\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"4\",\n" + 
			"        \"type\": \"STRING\"\n" + 
			"    },\n" + 
			"    \"f49\": {\n" + 
			"        \"format\": \"%03d\",\n" + 
			"        \"variable\": \"transaction_currency_code\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"3\",\n" + 
			"        \"type\": \"NUMERIC\"\n" + 
			"    },\n" + 
			"    \"f15\": {\n" + 
			"        \"format\": \"%-4s\",\n" + 
			"        \"variable\": \"settlement_date\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"4\",\n" + 
			"        \"type\": \"STRING\"\n" + 
			"    },\n" + 
			"    \"f37\": {\n" + 
			"        \"format\": \"%-12s\",\n" + 
			"        \"variable\": \"reference_number\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"12\",\n" + 
			"        \"type\": \"STRING\"\n" + 
			"    },\n" + 
			"    \"f48\": {\n" + 
			"        \"format\": \"%-621\",\n" + 
			"        \"variable\": \"f48\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"621\",\n" + 
			"        \"type\": \"LLLVAR\",\n" + 
			"		\"tlv\":true,\n" + 
			"		\"tags\":[\n" + 
			"			{\"name\":\"R1\", \"length\":34},\n" + 
			"			{\"name\":\"R2\", \"length\":34},\n" + 
			"			{\"name\":\"R3\", \"length\":34},\n" + 
			"		]\n" + 
			"    },\n" + 
			"    \"f57\": {\n" + 
			"        \"format\": \"%-621\",\n" + 
			"        \"variable\": \"f48\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"621\",\n" + 
			"        \"type\": \"LLLVAR\",\n" + 
			"		\"tlv\":true,\n" + 
			"		\"tags\":[\n" + 
			"			{\"name\":\"S1\", \"length\":34},\n" + 
			"			{\"name\":\"S2\", \"length\":34},\n" + 
			"			{\"name\":\"C3\", \"length\":34},\n" + 
			"		]\n" + 
			"    },\n" + 
			"    \"f2\": {\n" + 
			"        \"format\": \"%-19s\",\n" + 
			"        \"variable\": \"product_code\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"19\",\n" + 
			"        \"type\": \"LLVAR\"\n" + 
			"    },\n" + 
			"    \"f18\": {\n" + 
			"        \"format\": \"%04d\",\n" + 
			"        \"variable\": \"merchant_type\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"4\",\n" + 
			"        \"type\": \"NUMERIC\"\n" + 
			"    },\n" + 
			"    \"f3\": {\n" + 
			"        \"format\": \"%06d\",\n" + 
			"        \"variable\": \"processing_code\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"6\",\n" + 
			"        \"type\": \"NUMERIC\"\n" + 
			"    },\n" + 
			"    \"f39\": {\n" + 
			"        \"format\": \"%-2s\",\n" + 
			"        \"variable\": \"response_code\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"2\",\n" + 
			"        \"type\": \"STRING\"\n" + 
			"    },\n" + 
			"    \"f4\": {\n" + 
			"        \"format\": \"%012d\",\n" + 
			"        \"variable\": \"amount\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"12\",\n" + 
			"        \"type\": \"NUMERIC\"\n" + 
			"    },\n" + 
			"    \"f7\": {\n" + 
			"        \"format\": \"%-10s\",\n" + 
			"        \"variable\": \"transmission_date_time\",\n" + 
			"        \"options\": \"\",\n" + 
			"        \"field_length\": \"10\",\n" + 
			"        \"type\": \"DATE10\"\n" + 
			"    }\n" + 
			"}";
	public static void main(String[] args) throws Exception 
	{
		String iso = building();
		JSONObject json = parsing(iso);
	}
	private static JSONObject parsing(String iso) 
	{
		JSONObject map = new JSONObject(config);
		MessageTranslator mt = new MessageTranslator();
		JSONObject json = new JSONObject();
		
		json = mt.parseISO8583(iso, map);
		System.out.println(mt.royISO8583.showAsList());
		System.out.println(json.toString(4));
		return json;
	}
	private static String building()
	{
		MessageTranslator mt = new MessageTranslator();
		JSONObject map = new JSONObject(config);
		JSONObject data = new JSONObject();
		data.put("local_date", "1012");
		data.put("local_time", "101212");
		data.put("processing_code", "123456");
		data.put("processing_code", "123456");
		data.put("settlement_date", "1013");
		data.put("merchant_type", "6210");
		data.put("response_code", "21");
		data.put("amount", "6000000");
		data.put("reference_number", "123456789012");
		data.put("product_code", "12345");
		data.put("acq_institution_code", "111");
		data.put("transaction_currency_code", "360");
		data.put("acceptor_identification_code", "111111");
		data.put("stan", "123456");
		data.put("card_acceptor_terminal", "25423");
		data.put("biller_message", "Awas anjing galak");
		
		data.put("transmission_date_time", "1010101010");
		data.put("R1", "Ini Nilai R1");
		data.put("R2", "Yang Ini Nilai R2");
		data.put("R3", "Sedangkan Ini Nilai R3");
		data.put("S1", "Ini Nilai S1");
		data.put("S2", "Yang Ini Nilai S2");
		data.put("C3", "Sedangkan Ini Nilai C3");
		
		
		String iso = mt.buildISO8583(data, map, "0200");
		System.out.println(data.toString(4));
		System.out.println(mt.royISO8583.showAsList());
		System.out.println(iso);
		return iso;
		
	}
	private static void test2() throws Exception 
	{
		String iso = "";
		iso = "^A&0210f23ac4018ec1800000000000140000201993600918000002380612000600000000025000710042156000138111950071007100710601001108936000220636000200022397630000000000             C01000008000000019049PI04Q001CD30Pengguna ShopeePay            MC03UKE360089360091819936000220000000062302000000001950000005425";
		iso = "^AU0200f23ac4118ce18080000000001400000019936001530190000005436600000000001230007100600429248401259420710071107105812012C00000000089360015306360003000105207115682412             A0153944DapurAntiqDapur Antique 21         Jakarta BaratID049PI04Q001CD30Pengguna ShopeePay            MC03UMI36002061051144062070703A010893600918199360091800160523951";
		String len = iso.substring(0, 3);
		iso = iso.substring(3);
		RoyISO8583 royIso = new RoyISO8583();
		royIso.parse(iso);
		long messageLength1 = getLength(len.getBytes(), 3, true);
		long messageLength2 = getLength(len.getBytes(), 3, false);
		System.out.println("Little endian : "+messageLength1);
		System.out.println("Big endian    : "+messageLength2);
		System.out.println("ISO Message   : "+messageLength2);
		System.out.println("ISO           : "+royIso.showAsList());
		
	}
	public static void test1()
	{
		String configStr = "{\"f41\":{\"format\":\"%-8s\", \"variable\":\"card_acceptor_terminal\", \"options\":\"\", \"field_length\":\"8\", \"type\":\"STRING\"},\"f63\":{\"format\":\"%-32s%-30s%-50s%-18s\", \"variable\":\"locket_code, locket_name, locket_address, locket_phone\", \"options\":\"\", \"field_length\":\"130\", \"type\":\"LLLVAR\"},\"f32\":{\"format\":\"%-11s\", \"variable\":\"acq_institution_code\", \"options\":\"\", \"field_length\":\"11\", \"type\":\"LLVAR\"},\"f42\":{\"format\":\"%15s\", \"variable\":\"acceptor_identification_code\", \"options\":\"\", \"field_length\":\"15\", \"type\":\"STRING\"},\"f121\":{\"format\":\"%-32s\", \"variable\":\"payment_reference\", \"options\":\"\", \"field_length\":\"32\", \"type\":\"LLLVAR\"},\"f12\":{\"format\":\"%-6s\", \"variable\":\"local_time\", \"options\":\"\", \"field_length\":\"6\", \"type\":\"STRING\"},\"f120\":{\"format\":\"%-20s\", \"variable\":\"product_code\", \"options\":\"\", \"field_length\":\"20\", \"type\":\"LLLVAR\"},\"f11\":{\"format\":\"%06d\", \"variable\":\"stan\", \"options\":\"\", \"field_length\":\"6\", \"type\":\"NUMERIC\"},\"f33\":{\"format\":\"%-11s\", \"variable\":\"fwd_institution_code\", \"options\":\"\", \"field_length\":\"11\", \"type\":\"LLVAR\"},\"f13\":{\"format\":\"%-4s\", \"variable\":\"local_date\", \"options\":\"\", \"field_length\":\"4\", \"type\":\"STRING\"},\"f49\":{\"format\":\"%03d\", \"variable\":\"transaction_currency_code\", \"options\":\"\", \"field_length\":\"3\", \"type\":\"NUMERIC\"},\"f15\":{\"format\":\"%-4s\", \"variable\":\"settlement_date\", \"options\":\"\", \"field_length\":\"4\", \"type\":\"STRING\"},\"f37\":{\"format\":\"%012d\", \"variable\":\"reference_number\", \"options\":\"\", \"field_length\":\"12\", \"type\":\"NUMERIC\"},\"f48\":{\"format\":\"%11s%12s%01d\", \"variable\":\"meter_id, customer_id, id_selector\", \"options\":\"\", \"field_length\":\"24\", \"type\":\"LLLVAR\"},\"f2\":{\"format\":\"%-19s\", \"variable\":\"pan\", \"options\":\"\", \"field_length\":\"19\", \"type\":\"LLVAR\"},\"f18\":{\"format\":\"%04d\", \"variable\":\"merchant_type\", \"options\":\"\", \"field_length\":\"4\", \"type\":\"NUMERIC\"},\"f3\":{\"format\":\"%06d\", \"variable\":\"processing_code\", \"options\":\"\", \"field_length\":\"6\", \"type\":\"NUMERIC\"},\"f4\":{\"format\":\"%012d\", \"variable\":\"amount\", \"options\":\"\", \"field_length\":\"12\", \"type\":\"NUMERIC\"},\"f7\":{\"format\":\"%-10s\", \"variable\":\"transmission_date_time\", \"options\":\"\", \"field_length\":\"10\", \"type\":\"STRING\"},\"f127\":{\"format\":\"%-20s%-32s\", \"variable\":\"username, password\", \"options\":\"\", \"field_length\":\"52\", \"type\":\"LLLVAR\"}}";
		
		MessageTranslator mt = new MessageTranslator();
		String mti_id = "0210";
		String iso = "0200F23A400188C180060000000000000182196048200000002731   300000000000020000092513425200007213425209250926602111597        1112345      000000000072DEVALT0120090010080000014514987654321149999999911030E8597E3B2F1646505FDD6E210000090MUP210ZBE957561167FCD8506326E4AHAMDANIE LESTALUHUANI    R1  00000090000000090000000011653600505151106123            0600000000000000000000000000130                                ALTO                          Jalan Anggrek Neli Murni                          02199999          02000500050001         03214987654321                     052tester1             tester1                         ";
		String iso_new = "";
		String xml = "";
		JSONObject config = new JSONObject();
		JSONObject json = new JSONObject();
		
		try 
		{
			config = new JSONObject(configStr);
			json = mt.parseISO8583(iso, config);
			iso_new = new String(mt.buildISO8583(json, config, mti_id));
			xml = mt.buildXML(json, "data");
			
			System.out.println("Demonstration of conversion of ISO 8583 - JSON - XML");
			System.out.println("Config : ");
			System.out.println(config.toString());
			System.out.println("=============================================================");
			
			System.out.println("Original ISO 8583 : ");
			System.out.println("'"+iso+"'");
			System.out.println("Convert ISO to JSON");
			System.out.println("JSON : ");
			System.out.println(json.toString());
			System.out.println("=============================================================");
			
			System.out.println("Now, convert JSON to new ISO");
			System.out.println("New ISO 8583 : ");
			System.out.println("'"+iso_new+"'");
			System.out.println("=============================================================");
			
			System.out.println("Now, convert JSON to XML");
			System.out.println("XML : ");
			System.out.println(xml);
			System.out.println("=============================================================");
			
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Create header length
	 * @param messageLength Message length (in byte)
	 * @param headerLength Message header length
	 * @param direction Header direction (true = little endian, false = big endian)
	 * @return Byte represent length of message
	 */
	public static byte[] createISOLength(long messageLength, int headerLength, boolean direction)
	{
		int i;
		long j = 0;
		long k = 0;
		byte[] header = new byte[headerLength];
		if(direction)
		{
			// Little Endian
			j = messageLength;
			for(i = 0; i<headerLength; i++)
			{
				k = j % 256;
				header[i] = (byte) k;
				j = j / 256;
			}
		}
		else
		{
			// Big Endian
			j = messageLength;
			for(i = 0; i<headerLength; i++)
			{
				k = j % 256;
				header[headerLength-i-1] = (byte) k;
				j = j / 256;
			}
		}
		return header;
	}
	/**
	 * Convert integer to byte array
	 * @param i Integer value
	 * @return Byte array
	 */
	public static byte[] integerToByteArray(int i) 
	{
		  byte b[] = new byte[4];		  
		  ByteBuffer buf = ByteBuffer.wrap(b);
		  buf.putInt(i);
		  return b;
	}
	/**
	 * Convert byte array to integer	  
	 * @param b Byte array
	 * @return Integer value
	 */
	public static int byteArrayToInteger(byte[] b) 
	{
		  ByteBuffer buf = ByteBuffer.wrap(b);
		  return buf.getInt();
	}
	/**
	 * Convert byte array to hexadecimal
	 * @param bytes Input byte
	 * @return String in hexadecimal representation
	 */
	public static String byteArrayToHexadecimal(byte[] bytes)
	{
		StringBuilder sb = new StringBuilder();
	    for (byte b : bytes) 
	    {
	        sb.append(String.format("%02X ", b & 0xFF));
	    }
	    return sb.toString().trim();
	}
	/**
	 * Get actual message length received from member
	 * @param message Raw message
	 * @param headerLength Message header length Header length
	 * @param headerDirectionRequest Header direction. true = LSB left MSB right, false = MSB left LSB right
	 * @return Actual message length
	 * @throws Exception 
	 * @throws NegativeLengthException if message length is lower than 0
	 */
    public static int getLength(byte[] message, int headerLength, boolean headerDirectionRequest) throws Exception 
    {
      	int i;
    	int result = 0;
    	int x = 0;
    	if(headerDirectionRequest)
    	{
    	   	// Little endian
    		for(i = headerLength-1; i >= 0; i--)
	    	{
	    		result *= 256;
	    		x = (byte) message[i];
	    		if(x < 0)
	    		{
	    			x = x+256;
	    		}
	    		if(x > 256)
	    		{
	    			x = x-256;
	    		}
	    		result += x;
	    	}  		
    	}
    	else
    	{
    		// Big endian
	    	for(i = 0; i < headerLength; i++)
	    	{
	    		result *= 256;
	    		x = (byte) message[i];
	    		if(x < 0)
	    		{
	    			x = x+256;
	    		}
	    		if(x > 256)
	    		{
	    			x = x-256;
	    		}
	    		result += x;
	    	}
    	}
    	if(result < 0)
    	{
    		throw new Exception("Message length must greater than or equal to 0.");
    	}
    	return result;
    }
    /**
     * Concatenate 2 byte array
     * @param firstByte First bytes
     * @param secondByte Second bytes
     * @return Concatenated bytes
     */
	public static byte[] byteConcate(byte[] firstByte, byte[] secondByte)
	{
		byte[] z = new byte[firstByte.length+secondByte.length];
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try 
		{
			outputStream.write(firstByte);
			outputStream.write(secondByte);
			z = outputStream.toByteArray();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return z;
	}
    /**
     * Concatenate 3 byte array
     * @param firstByte First bytes
     * @param secondByte Second bytes
     * @param thirdByte Third bytes
     * @return Concatenated bytes
     */
	public static byte[] byteConcate(byte[] firstByte, byte[] secondByte, byte[] thirdByte)
	{
		byte[] z = new byte[firstByte.length+secondByte.length+thirdByte.length];
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
		try 
		{
			outputStream.write(firstByte);
			outputStream.write(secondByte);
			outputStream.write(thirdByte);
			z = outputStream.toByteArray();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return z;
	}
    /**
     * Concatenate 3 byte array
     * @param firstByte First bytes
     * @param secondByte Second bytes
     * @param thirdByte Third bytes
     * @param fourthByte Fourth byte
     * @return Concatenated bytes
     */
	public static byte[] byteConcate(byte[] firstByte, byte[] secondByte, byte[] thirdByte, byte[] fourthByte)
	{
		byte[] z = new byte[firstByte.length+secondByte.length+thirdByte.length];
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
		try 
		{
			outputStream.write(firstByte);
			outputStream.write(secondByte);
			outputStream.write(thirdByte);
			outputStream.write(fourthByte);
			z = outputStream.toByteArray();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return z;
	}
}
