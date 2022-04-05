package StepsDefinition;

import Util.ExecuteYaml;
import Util.TestBase;
import bean.Page;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Steps {
    public WebDriver driver;
    TestBase testBase;
    ExecuteYaml execute;
    Map<String, Page> map;
    Page page;
    Map<String,String> mapSaveText;

    public Map<String, Page> getMap() {
        return map;
    }

    public Steps() {
    }
    @Before
    public void setUp(){
        testBase = new TestBase();
        execute = new ExecuteYaml();
        map = new HashMap<>();
        page = new Page();
        mapSaveText = new HashMap<>();
    }
    @Given("I Navigate with URl is {string}")
    public void openBrowser(String url) {
        if(driver ==null){
            driver = testBase.getDriver();
        }
        testBase.OpenBrowser(this.driver,url);
    }
    @Given("I change the page spec to {word}")
    public void updateYaml(String yaml) {
       page= execute.updateYaml(yaml,map);
    }
    @Given("I {word} element {word}")
    public void mouseAction(String action, String element) {
        testBase.mouseAction(this.page,action,this.driver,element);
    }
    @When("I type {string} into element {word}")
    public void actionType(String content, String element) {
        testBase.ActionType(this.page,this.driver,element,content,this.mapSaveText);

    }
    @And("I wait for element {word} to be {word}")
    public void waitTo(String element,String status) {
        testBase.showUI(this.page,this.driver,element,status);
    }
    @And("I verify the text for element {word} is {string}")
    public void verifyText(String element, String text) {
        testBase.verifyText(this.page,this.driver, element,text,true,this.mapSaveText);
    }
    @And("I verify the exact text for element {word} is {string}")
    public void verifyExactText(String element, String text) {
        testBase.verifyText(this.page,this.driver, element,text,false, this.mapSaveText);
    }
    @Given("I save the text for element {word} is {string}")
    public void saveText(String element, String text) {
        testBase.saveTextElement(this.page, this.driver, element,text, this.mapSaveText);
    }
    @Given("I {word} text from element {word}")
    public void clearText(String action, String element) {
        testBase.mouseAction(this.page,action,this.driver,element);
    }
    @Given("I scroll to element {word}")
    public void isScrollToElement(String element) {
        testBase.scrollAction(this.driver, element, this.page);
    }
    @Given("I perform to action {word}")
    public void i_perform_to_action(String action) {
        testBase.executeAction(this.driver,this.page,action,null);

    }
    @Given("I perform to action {word} with override values")
    public void actionOverride(String action,DataTable dataTable) {
        testBase.executeAction(this.driver,this.page,action, dataTable);
//        System.out.println("dataTable.asList().size()= "+ dataTable.asMap(String.class,String.class));
//        Map<String,String> map = dataTable.asMap(String.class,String.class);
//        System.out.println("dataTable.asList().size()= "+ map.keySet());
//        System.out.println("map.getKey"+ map);
//        System.out.println("thang=== " +dataTable.row());
    }
    @After
    public void tearDown(){
        this.driver.close();
    }

}
