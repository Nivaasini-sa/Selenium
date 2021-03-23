import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SendMailWithAttachment {

	  public static void main(String[] args) throws IOException, InterruptedException {

			  Scanner kb = new Scanner(System.in); 
		          System.out.println("enter ur id: ");
			  String id = kb.nextLine(); 
		          System.out.println("enter ur pass: "); String
			  password = kb.nextLine();
			  System.out.println("enter email-id to whom u want to send the mail: ");
			  String toId = kb.nextLine(); 
		          System.out.println("enter subject for mail: ");
			  String subject = kb.nextLine();
			  System.out.println("enter the content of the mail: ");
		          String content=kb.nextLine();

		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\nsa2\\Desktop\\Automation\\chromedriver.exe");
		 WebDriver driver = new ChromeDriver();
		  
		// open Gmail
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("https://accounts.google.com/signin/v2/identifier?hl=en&passive=true&continue=https%3A%2F%2Fwww.google.com%2Fsearch%3Fsafe%3Dactive%26q%3DGmail%2Blogin%2Bcomposing%2Bof%2Bmail%2Band%2Battachment%2Btest%2Bscript%2Bwith%2Bselenium%2Bjava%26rlz%3D1C1CHBF_enIN873IN873%26oq%3DGmail%2Blogin%2B%26aqs%3Dchrome.0.69i59l3j69i57j0i271j69i60j69i61.4354j0j1%26sourceid%3Dchrome%26ie%3DUTF-8%26safe%3Dactive&ec=GAZAAQ&flowName=GlifWebSignIn&flowEntry=ServiceLogin");		
		
		//login to gmail
        driver.findElement(By.id ("identifierId")).sendKeys(id);
        driver.findElement(By.xpath("//input[@id='Passwd']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        driver.findElement(By.name("signIn")).click();
       
        //click on compose and add the to mail id, and subject
        driver.findElement(By.xpath("//div[text()='COMPOSE']")).click();
        driver.findElement(By.xpath("//form[1]//textarea[1]")).sendKeys(toId);
        driver.findElement(By.xpath("//div[@class='aoD az6']//input[@class='aoT']")).sendKeys(subject);
       
        //wirte the mail body, that mail body is under frame so 1st switch the control to frame then write
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@tabindex='1']")));
        driver.findElement(By.xpath("//body[@class='editable LW-avf']")).sendKeys(content);
        driver.switchTo().defaultContent(); // again switch back to main page
       
        //click on attachment
        driver.findElement(By.xpath("//div[@class='a1 aaA aMZ']")).click();
        //use autoit tool to attach a file
        Runtime.getRuntime().exec("C:\\selenium\\AutoIT\\fileUpload.exe");
        Thread.sleep(10000); //wait for 10sec to upload file
       
        //click on send
        driver.findElement(By.xpath("//div[text()='Send']")).click();
        String msg = driver.findElement(By.xpath("//div[contains(text(),'Your message has been sent.')]")).getText();
        String exp = "Your message has been sent. View message";
        System.out.println("pass");
        driver.close();
    }
}

