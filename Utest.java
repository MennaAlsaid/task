
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;


@RunWith(MockitoJUnitRunner.class)
public class Utest {
	DAOImpl newDAOobj=new DAOImpl();
	Product  product;
	   @Mock 
	   Connection conn;
	   @Mock
	   PreparedStatement psmt;
	   @InjectMocks
	   DAOImpl newDAO = new DAOImpl();
	  
	   @ Test
	   public void testproCon()
	   {
	   	Product pro = new Product(8);
	   	Assert.assertEquals(8,pro.getId());
	   	} 
	   
	   @Test
	   public void  tsettype(){
	   
	   Product pro= new Product(8);
	   pro.setType("pepsi");
	   Assert.assertEquals("pepsi", pro.getType());
	   
	   }
	   
	   @Test
	   public void  tsetManufacturer(){
	    Product pro= new Product(8);
	    pro.setManufacturer("pepsi");
	    Assert.assertEquals("pepsi", pro.getManufacturer()); 
	    }
	   
	   @Test
	   public void  tsetProductionDate(){
	   Product pro= new Product(8);
	   pro.setProductionDate("2012");
	   Assert.assertEquals("2012", pro.getProductionDate());
	   }
	
	   @Test
	   public void  tsetExpirayDate(){
	   Product pro= new Product(8);
	   pro.setExpiryDate("2000");
	   Assert.assertEquals("2000", pro.getExpiryDate());
	   
	   }
	   
	   
	   @Test (expected = DAOException.class)
       public void ExceptionCase() throws SQLException, DAOException{
	    when(conn.prepareStatement(anyString())).thenReturn(psmt);
	    when(psmt.executeUpdate()).thenThrow(new SQLException());
	    Product pro= new Product(8);
	    newDAO.insertProduct(pro);
}
		
	  @Test
	   public void HappyTest2() throws SQLException, DAOException{
	   	when(conn.prepareStatement(anyString())).thenReturn(psmt);
	   	ArgumentCaptor<String> stringcaptor = ArgumentCaptor.forClass(String.class);
	 	ArgumentCaptor<Integer> intcaptor = ArgumentCaptor.forClass(int.class);
	  	Product pro= new Product(3);
	   	newDAO.insertProduct(pro);
	   	verify(psmt, times(4)).setString(anyInt(), stringcaptor.capture());
	   	verify(psmt, times(1)).setInt(anyInt(), intcaptor.capture());
	   	Assert.assertTrue(stringcaptor.getAllValues().get(0).equals(3));
	   }

        
	   
	 
	   
	   @Test

	   public void integrationTestdelete() throws SQLException, DAOException{
	     product= new Product(7);
	     product.setType("pepsi");
	     product.setManufacturer("pepsi");
	     product.setProductionDate("2016");
	     product.setExpiryDate("2019");
	     newDAOobj.insertProduct( product);
	     Assert.assertNotNull( newDAOobj.getProduct(7));
	     newDAOobj.deleteProduct(7);
	     Assert.assertNull( newDAOobj.getProduct(7));
	   }
	     
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
}
