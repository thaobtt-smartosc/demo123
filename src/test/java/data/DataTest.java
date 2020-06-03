package data;
import java.io.File;
import java.io.IOException;


import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
public class DataTest {

	JSONObject jsonObject, product;
	
	public DataTest() {
		File file= new File(System.getProperty("user.dir") + "/product.json");
		
		try {
			product = new JSONObject(FileUtils.readFileToString(file,"utf-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public Product getProduct(String id) {
		jsonObject = (JSONObject) product.get(id);

		return new Product(jsonObject.getString("url"),
				jsonObject.getString("name"),
				jsonObject.getString("price"),
				jsonObject.getString("color"),
				jsonObject.getString("size"));
	}

   
	
	
}
