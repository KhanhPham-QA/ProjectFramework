package khanhpham.keywords;

import khanhpham.constants.DataConfig;
import khanhpham.driver.DriverManager;
import khanhpham.helpers.LogHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ScrollMobile {
    private static final int TIMEOUT_SCROLL_DEFAULT = Integer.parseInt(DataConfig.TIMEOUT_SCROLL_DEFAULT);
    private static final int NUMBER_RETRY = Integer.parseInt(DataConfig.NUMBER_RETRY);

    private static Point calCenterElement (Point location, Dimension size){
        return new Point(location.getX() + size.getWidth() / 2, location.getY() + size.getHeight() / 2);
    }

    private static Dimension sizeScreen (){
        return DriverManager.getDriver().manage().window().getSize();
    }

    public static void tapCenterElement (WebElement element){
        try {
            Point location = element.getLocation();
            Dimension size = element.getSize();
            Point getCenterElement = calCenterElement(location, size);
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
            Sequence sequence = new Sequence(finger,1);
            sequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),getCenterElement));
            sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            sequence.addAction(new Pause(finger,Duration.ofMillis(300)));
            sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            DriverManager.getDriver().perform(Collections.singleton(sequence));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when the execution click center of element: " + element + " - " + e.getMessage());
        }
    }

    public static void swipeUpCenterOfElement (WebElement element, int millisSecond){
        try {
            Point location = element.getLocation();
            Dimension size = element.getSize();
            Point getCenterElement = calCenterElement(location, size);
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
            Sequence sequence = new Sequence(finger,1);
            sequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),getCenterElement));
            sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            sequence.addAction(finger.createPointerMove(Duration.ofMillis(millisSecond), PointerInput.Origin.viewport(),getCenterElement.getX(), getCenterElement.getY() - 300));
            sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            DriverManager.getDriver().perform(Collections.singleton(sequence));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when the execution swipe up center of element: " + element + " - " + e.getMessage());
        }
    }

    public static void swipeUpCenterOfElement (WebElement element){
        swipeUpCenterOfElement(element, TIMEOUT_SCROLL_DEFAULT);
    }

    public static void swipeDownCenterOfElement (WebElement element, int millisSecond){
        try {
            Point location = element.getLocation();
            Dimension size = element.getSize();
            Point getCenterElement = calCenterElement(location, size);
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
            Sequence sequence = new Sequence(finger,1);
            sequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),getCenterElement));
            sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            sequence.addAction(finger.createPointerMove(Duration.ofMillis(millisSecond), PointerInput.Origin.viewport(),getCenterElement.getX(), getCenterElement.getY() + 300));
            sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            DriverManager.getDriver().perform(Collections.singleton(sequence));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when the execution swipe down center of element: " + element + " - " + e.getMessage());
        }
    }

    public static void swipeDownCenterOfElement (WebElement element){
        swipeDownCenterOfElement(element, TIMEOUT_SCROLL_DEFAULT);
    }

    public static void swipeRightCenterOfElement (WebElement element, int millisSecond){
        try {
            Point location = element.getLocation();
            Dimension size = element.getSize();
            Point getCenterElement = calCenterElement(location, size);
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
            Sequence sequence = new Sequence(finger,1);
            sequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),getCenterElement));
            sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            sequence.addAction(finger.createPointerMove(Duration.ofMillis(millisSecond), PointerInput.Origin.viewport(),getCenterElement.getX() + 150, getCenterElement.getY()));
            sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            DriverManager.getDriver().perform(Collections.singleton(sequence));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when the execution swipe right center of element: " + element + " - " + e.getMessage());
        }
    }

    public static void swipeRightCenterOfElement (WebElement element){
        swipeRightCenterOfElement(element, TIMEOUT_SCROLL_DEFAULT);
    }

    public static void swipeLeftCenterOfElement (WebElement element, int millisSecond){
        try {
            Point location = element.getLocation();
            Dimension size = element.getSize();
            Point getCenterElement = calCenterElement(location, size);
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
            Sequence sequence = new Sequence(finger,1);
            sequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),getCenterElement));
            sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            sequence.addAction(finger.createPointerMove(Duration.ofMillis(millisSecond), PointerInput.Origin.viewport(),getCenterElement.getX() - 150, getCenterElement.getY()));
            sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            DriverManager.getDriver().perform(Collections.singleton(sequence));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when the execution swipe left center of element: " + element + " - " + e.getMessage());
        }
    }

    public static void swipeLeftCenterOfElement (WebElement element){
        swipeLeftCenterOfElement(element, TIMEOUT_SCROLL_DEFAULT);
    }

    public static void swipeUp (int millisSecond){
        try {
            int startX = (int) (sizeScreen().getWidth() * 0.5);
            int startY = (int) (sizeScreen().getHeight() * 0.5);
            int endX = startX;
            int endY = (int) (sizeScreen().getHeight() * 0.2);
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
            Sequence sequence = new Sequence(finger,1);
            sequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
            sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            sequence.addAction(finger.createPointerMove(Duration.ofMillis(millisSecond), PointerInput.Origin.viewport(), endX, endY));
            sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            DriverManager.getDriver().perform(Collections.singleton(sequence));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when the execution swipe up on the screen - " + e.getMessage());
        }
    }

    public static void swipeUp (){
        swipeUp(TIMEOUT_SCROLL_DEFAULT);
    }

    public static void swipeDown (int millisSecond){
        try {
            int startX = (int) (sizeScreen().getWidth() * 0.5);
            int startY = (int) (sizeScreen().getHeight() * 0.5);
            int endX = startX;
            int endY = (int) (sizeScreen().getHeight() * 0.8);
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
            Sequence sequence = new Sequence(finger,1);
            sequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
            sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            sequence.addAction(finger.createPointerMove(Duration.ofMillis(millisSecond), PointerInput.Origin.viewport(), endX, endY));
            sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            DriverManager.getDriver().perform(Collections.singleton(sequence));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when the execution swipe down on the screen - " + e.getMessage());
        }
    }

    public static void swipeDown (){
        swipeDown(TIMEOUT_SCROLL_DEFAULT);
    }

    public static void swipeRight (int millisSecond){
        try {
            int startX = (int) (sizeScreen().getWidth() * 0.5);
            int startY = (int) (sizeScreen().getHeight() * 0.5);
            int endX = (int) (sizeScreen().getWidth() * 0.8);
            int endY = startY;
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
            Sequence sequence = new Sequence(finger,1);
            sequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
            sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            sequence.addAction(finger.createPointerMove(Duration.ofMillis(millisSecond), PointerInput.Origin.viewport(), endX, endY));
            sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            DriverManager.getDriver().perform(Collections.singleton(sequence));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when the execution swipe right on the screen - " + e.getMessage());
        }
    }

    public static void swipeRight (){
        swipeRight(TIMEOUT_SCROLL_DEFAULT);
    }

    public static void swipeLeft (int millisSecond){
        try {
            int startX = (int) (sizeScreen().getWidth() * 0.5);
            int startY = (int) (sizeScreen().getHeight() * 0.5);
            int endX = (int) (sizeScreen().getWidth() * 0.2);
            int endY = startY;
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
            Sequence sequence = new Sequence(finger,1);
            sequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
            sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            sequence.addAction(finger.createPointerMove(Duration.ofMillis(millisSecond), PointerInput.Origin.viewport(), endX, endY));
            sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            DriverManager.getDriver().perform(Collections.singleton(sequence));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when the execution swipe left on the screen - " + e.getMessage());
        }
    }

    public static void swipeLeft (){
        swipeLeft(TIMEOUT_SCROLL_DEFAULT);
    }

    public static void scrollUpByGesture (){
        try {
            Map<String, Object> scrollGesture = new HashMap<>();
            scrollGesture.put("left", sizeScreen().getWidth() * 0.5);
            scrollGesture.put("top", sizeScreen().getHeight() * 0.25);
            scrollGesture.put("width", sizeScreen().getWidth() * 0.4);
            scrollGesture.put("height", sizeScreen().getHeight() * 0.6);
            scrollGesture.put("direction", "down");
            scrollGesture.put("speed", 2500);
            scrollGesture.put("percent", 1);
            DriverManager.getDriver().executeScript("mobile: scrollGesture", scrollGesture);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when the execution scroll up by gesture on the screen - " + e.getMessage());
        }
    }

    public static void scrollDownByGesture (){
        try {
            Map<String, Object> scrollGesture = new HashMap<>();
            scrollGesture.put("left", sizeScreen().getWidth() * 0.5);
            scrollGesture.put("top", sizeScreen().getHeight() * 0.6);
            scrollGesture.put("width", sizeScreen().getWidth() * 0.4);
            scrollGesture.put("height", sizeScreen().getHeight() * 0.25);
            scrollGesture.put("direction", "up");
            scrollGesture.put("speed", 2500);
            scrollGesture.put("percent", 1);
            DriverManager.getDriver().executeScript("mobile: scrollGesture", scrollGesture);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when the execution scroll down by gesture on the screen - " + e.getMessage());
        }
    }

    public static boolean scrollUpToFindElement (By locator, int numberOfScroll){
        try {
            LogHelper.info("Ready to scroll up finding element: " + locator);
            for (int i = 0; i < numberOfScroll; i++){
                if (MobileUI.isDisplay(locator,1)) {
                    LogHelper.info("Element: " + locator + " is display on the screen with: " + (i + 1) + " scroll up");
                    return true;
                }
                LogHelper.info("Scroll up to find element: " + locator + " in: '" + (i + 1) + " scroll'");
                swipeUp();
            }
            LogHelper.info("Element: " + locator + " can't display on the screen");
            return false;
        } catch (Exception e){
            LogHelper.error("An error occurred when trying to scroll finding element: " + locator + " - " + e.getMessage());
            return false;
        }
    }

    public static boolean scrollUpToFindElement (By locator){
        return scrollUpToFindElement(locator, NUMBER_RETRY);
    }

    public static boolean scrollDownToFindElement (By locator, int numberOfScroll){
        try {
            LogHelper.info("Ready to scroll down finding element: " + locator);
            for (int i = 0; i < numberOfScroll; i++){
                if (MobileUI.isDisplay(locator,1)) {
                    LogHelper.info("Element: " + locator + " is display on the screen with: " + (i + 1) + " scroll down");
                    return true;
                }
                LogHelper.info("Scroll down to find element: " + locator + " in: '" + (i + 1) + " scroll'");
                swipeDown();
            }
            LogHelper.info("Element: " + locator + " can't display on the screen");
            return false;
        } catch (Exception e){
            LogHelper.error("An error occurred when trying to scroll finding element: " + locator + " - " + e.getMessage());
            return false;
        }
    }

    public static boolean scrollDownToFindElement (By locator){
        return scrollDownToFindElement(locator, NUMBER_RETRY);
    }

    public static boolean scrollRightToFindElement (By locator, int numberOfScroll){
        try {
            LogHelper.info("Ready to scroll right finding element: " + locator);
            for (int i = 0; i < numberOfScroll; i++){
                if (MobileUI.isDisplay(locator,1)) {
                    LogHelper.info("Element: " + locator + " is display on the screen with: " + (i + 1) + " scroll right");
                    return true;
                }
                LogHelper.info("Scroll right to find element: " + locator + " in: '" + (i + 1) + " scroll'");
                swipeRight();
            }
            LogHelper.info("Element: " + locator + " can't display on the screen");
            return false;
        } catch (Exception e){
            LogHelper.error("An error occurred when trying to scroll finding element: " + locator + " - " + e.getMessage());
            return false;
        }
    }

    public static boolean scrollRightToFindElement (By locator){
        return scrollRightToFindElement(locator, NUMBER_RETRY);
    }

    public static boolean scrollLeftToFindElement (By locator, int numberOfScroll){
        try {
            LogHelper.info("Ready to scroll left finding element: " + locator);
            for (int i = 0; i < numberOfScroll; i++){
                if (MobileUI.isDisplay(locator,1)) {
                    LogHelper.info("Element: " + locator + " is display on the screen with: " + (i + 1) + " scroll left");
                    return true;
                }
                LogHelper.info("Scroll left to find element: " + locator + " in: '" + (i + 1) + " scroll'");
                swipeLeft();
            }
            LogHelper.info("Element: " + locator + " can't display on the screen");
            return false;
        } catch (Exception e){
            LogHelper.error("An error occurred when trying to scroll finding element: " + locator + " - " + e.getMessage());
            return false;
        }
    }

    public static boolean scrollLeftToFindElement (By locator){
        return scrollLeftToFindElement(locator, NUMBER_RETRY);
    }
}
