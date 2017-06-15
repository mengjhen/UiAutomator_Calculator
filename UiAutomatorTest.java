package com.example.calculatorautomationtest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import android.util.Log;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class UiAutomatorTest
{
    private static final String BASIC_SAMPLE_PACKAGE = "com.android.calculator2";
    private static final int LAUNCH_TIMEOUT = 30000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator";
    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen()
    {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        // Start from the home screen
        mDevice.pressHome();
        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);
        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        // Wait for the app to appear
        SystemClock.sleep(10000);
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

//    @Test
//    public void simpleAddTest() throws UiObjectNotFoundException
//    {
//        UiObject keyOne = mDevice.findObject(new UiSelector().text("1").className("android.widget.Button"));
//        UiObject keyPlus = mDevice.findObject(new UiSelector().text("+").className("android.widget.Button"));
//        UiObject keyEqual = mDevice.findObject(new UiSelector().text("=").className("android.widget.Button"));
//        assertTrue(keyOne.exists());
//        assertTrue(keyPlus.exists());
//        assertTrue(keyEqual.exists());
//        keyOne.click();
//        keyPlus.click();
//        keyOne.click();
//        keyEqual.click();
//        UiObject answerBar = mDevice.findObject(new UiSelector().resourceId("com.android.calculator2:id/result"));
//        String answer = answerBar.getText();
//      3  assertEquals("2", answer);
//    }

    @Test
    public void simpleAddTest() throws UiObjectNotFoundException
    {
        UiObject2 keyOne = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "digit_1"));
        UiObject2 keyTwo = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "digit_2"));
        UiObject2 keyplus = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "op_add"));
        UiObject2 keyEqual = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "eq"));

        keyTwo.click();
        keyplus.click();
        keyOne.click();
        keyEqual.click();

        UiObject2 result = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "result")), 5000);

        assertEquals("3", result.getText());
    }

    @Test
    public void simpleMinusTest() throws UiObjectNotFoundException
    {
        UiObject2 keyOne = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "digit_1"));
        UiObject2 keyTwo = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "digit_2"));
        UiObject2 keyMinus = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "op_sub"));
        UiObject2 keyEqual = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "eq"));

        keyTwo.click();
        keyMinus.click();
        keyOne.click();
        keyEqual.click();

        UiObject2 result = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "result")), 5000);

        assertEquals("1", result.getText());
    }

    @Test
    public void simplemultiplyTest() throws UiObjectNotFoundException
    {
        UiObject2 keyOne = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "digit_1"));
        UiObject2 keyTwo = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "digit_2"));
        UiObject2 keyMultiply = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "op_mul"));
        UiObject2 keyEqual = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "eq"));

        keyOne.click();
        keyMultiply.click();
        keyTwo.click();
        keyEqual.click();

        UiObject2 result = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "result")), 5000);

        assertEquals("2", result.getText());
    }

    @Test
    public void simpledivideTest() throws UiObjectNotFoundException
    {
        UiObject2 keyOne = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "digit_1"));
        UiObject2 keyTwo = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "digit_2"));
        UiObject2 keydivide = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "op_div"));
        UiObject2 keyEqual = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "eq"));

        keyTwo.click();
        keydivide.click();
        keyOne.click();
        keyEqual.click();

        UiObject2 result = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "result")), 5000);

        assertEquals("2", result.getText());
    }

    @Test
    public void simpleAdvancedPadTest()
    {
        UiObject2 padPager = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "pad_pager"));
        List<UiObject2> padChild = padPager.getChildren();
        UiObject2 padAdvance = padChild.get(1);
        Point padPoint = padAdvance.getVisibleCenter();
        UiObject2 buttonFour = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "digit_4"));
        Point btnPoint = buttonFour.getVisibleCenter();
        Point[] coor = new Point[2];
        coor[0] = padPoint;
        coor[1] = btnPoint;
        mDevice.swipe(coor, 5);

        assertTrue(mDevice.hasObject(By.res(BASIC_SAMPLE_PACKAGE, "op_pow")));

        SystemClock.sleep(3000);
        coor[1] = padPoint;
        coor[0] = btnPoint;
        mDevice.swipe(coor, 5);
    }


    @Test
    public void showLicenseTest()
    {
        UiObject2 moreBtn = mDevice.findObject(By.desc("More options"));
        moreBtn.click();

        mDevice.wait(Until.findObject(By.text("Open source licenses")), 5000);
        mDevice.findObject(By.text("Open source licenses")).click();
        mDevice.wait(Until.findObject(By.text("Notices for files:")), 5000);
        mDevice.pressBack();
    }

    @Test
    public void smallTest() throws Exception
    {
        CalculatorObjectClass obj = null;
        try
        {
            obj = new CalculatorObjectClass(mDevice);
        }
        catch (Exception e)
        {
            throw e;
        }

        obj.log("hello");
        obj.initCalculator();
        CalculatorObjectClass ansObj = obj.add(1,2);
        assertEquals("3", ansObj.getResult());
    }

}


class CalculatorObjectClass
{
    private static final String BASIC_SAMPLE_PACKAGE = "com.android.calculator2";
    private static final int LAUNCH_TIMEOUT = 500;
    private static final String LOGTAG = "UiAutomator";
    private UiDevice mDevice;

    public CalculatorObjectClass(UiDevice device) throws Exception
    {
        //this.mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice = device;
        for(int i=0; i<10 ; i++)
        {
            String a = "digit_" + String.valueOf(i);
            this.log(a);
            if(!this.hasObject(a))
            {
                throw new Exception("Calculator object create failed");
            }
        }

        String[] opArray = {"op_add", "op_sub", "op_mul", "op_div", "eq", "dec_point", "pad_advanced"};

        for (String anOpArray : opArray)
        {
            //this.log(anOpArray);
            if(!this.hasObject(anOpArray))
            {
                throw new Exception("Calculator object create failed");
            }
        }
        if (!this.hasObject("clr") && !this.hasObject("del"))
        {
            throw new Exception("Calculator object create failed");
        }
    }

    private boolean hasObject(String id)
    {
        return mDevice.hasObject(By.res(BASIC_SAMPLE_PACKAGE, id));
    }

    private UiObject2 getUiObject2(String id)
    {
        return mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, id));
    }

    public void log(String msg)
    {
        Log.d(LOGTAG, msg);
    }

    public void initCalculator()
    {
        if (this.hasObject("clr"))
        {
            this.log("clr click");
            getUiObject2("clr").click();
        } else
            {
            this.log("del click");
            while(this.hasObject("formula"))
            {
                if(this.getUiObject2("formula").getText() == null)
                {
                    break;
                }

                this.getUiObject2("del").click();
            }
        }
    }

    public CalculatorObjectClass add(int number1, int number2)
    {
        this.getUiObject2("digit_" + String.valueOf(number1)).click();
        this.getUiObject2("op_add").click();
        this.getUiObject2("digit_" + String.valueOf(number2)).click();
        this.getUiObject2("eq").click();

        return this;
    }

    public String getResult()
    {
        if (this.hasObject("result"))
        {
            return this.getUiObject2("result").getText();
        }
        else
            {
                return null;
            }
    }

}