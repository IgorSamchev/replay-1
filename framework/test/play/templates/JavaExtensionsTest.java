package play.templates;

import org.codehaus.groovy.runtime.NullObject;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;


public class JavaExtensionsTest {

    @BeforeClass
    public static void setUpBeforeClass() {
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testContains()  {
        String[] testArray = {"a", "b", "c"};
        assertTrue(JavaExtensions.contains(testArray, "a"));
        assertFalse(JavaExtensions.contains(testArray, "1"));
    }

    @Test
    public void testAdd()  {
        String[] testArray = {"a", "b", "c"};
        assertThat(JavaExtensions.add(new String[]{"a", "b"}, "c")).hasSize(3).contains(testArray);
        
    }

    @Test 
    public void testRemove()  {
        String[] testArray = {"a", "b", "c"};
        assertThat(JavaExtensions.remove(testArray, "c")).hasSize(2).contains("a", "b");
    }

    @Test
    public void testCapitalizeWords()  {
        assertThat(JavaExtensions.capitalizeWords("This is a small   test!")).isEqualTo("This Is A Small   Test!");
    }

    @Test 
    public void testPad()  {
        assertThat(JavaExtensions.pad("12345", 4)).isEqualTo("12345");
        assertThat(JavaExtensions.pad("12345", 5)).isEqualTo("12345");
        assertThat(JavaExtensions.pad("12345", 6)).isEqualTo("12345&nbsp;");
        assertThat(JavaExtensions.pad("12345", 8)).isEqualTo("12345&nbsp;&nbsp;&nbsp;");
    }

    @Test
    public void testEscapeJavaScript() {
        assertThat(JavaExtensions.escapeJavaScript("'Hello/world'")).isEqualTo("\\'Hello\\/world\\'");
        assertThat(JavaExtensions.escapeJavaScript("\u0001Привет\t你好\n")).isEqualTo("\\u0001Привет\\t你好\\n");
    }

    @Test
    public void testPluralizeNumber() {
        assertEquals("s", JavaExtensions.pluralize(0));
        assertEquals("", JavaExtensions.pluralize(1));
        assertEquals("s", JavaExtensions.pluralize(2));
    }

    @Test
    public void testPluralizeCollection() {
        List <String> testCollection = new ArrayList<>();
        assertEquals("s", JavaExtensions.pluralize(testCollection));
        testCollection.add("1");
        assertEquals("", JavaExtensions.pluralize(testCollection));
        testCollection.add("2");
        assertEquals("s", JavaExtensions.pluralize(testCollection));
    }

    @Test
    public void testPluralizeNumberString() {
        String plural = "n";
        assertEquals(plural, JavaExtensions.pluralize(0, plural));
        assertEquals("", JavaExtensions.pluralize(1, plural));
        assertEquals(plural, JavaExtensions.pluralize(2, plural));
    }

    @Test
    public void testPluralizeCollectionString() {
        String plural = "n";
        List <String> testCollection = new ArrayList<>();
        assertEquals(plural, JavaExtensions.pluralize(testCollection, plural));
        testCollection.add("1");
        assertEquals("", JavaExtensions.pluralize(testCollection, plural));
        testCollection.add("2");
        assertEquals(plural, JavaExtensions.pluralize(testCollection, plural));
    }

    @Test
    public void testPluralizeNumberStringArray() {
        String[] forms = {"Test", "Tests"};
        assertEquals(forms[1], JavaExtensions.pluralize(0, forms));
        assertEquals(forms[0], JavaExtensions.pluralize(1, forms));
        assertEquals(forms[1], JavaExtensions.pluralize(2, forms));

    }

    @Test
    public void testPluralizeCollectionStringArray() {
        String[] forms = {"Test", "Tests"};
        List <String> testCollection = new ArrayList<>();
        assertEquals(forms[1], JavaExtensions.pluralize(testCollection, forms));
        testCollection.add("1");
        assertEquals(forms[0], JavaExtensions.pluralize(testCollection, forms));
        testCollection.add("2");
        assertEquals(forms[1], JavaExtensions.pluralize(testCollection, forms));
    }

    @Test
    public void testYesNo()  {
        String yes = "Y";
        String no = "N";
        String[] yesNo = {yes, no};
        assertEquals(no, JavaExtensions.yesno(null, yesNo));
        assertEquals(no, JavaExtensions.yesno(Boolean.FALSE, yesNo));
        assertEquals(yes, JavaExtensions.yesno(Boolean.TRUE, yesNo));
        //String
        assertEquals(no, JavaExtensions.yesno("", yesNo));
        assertEquals(yes, JavaExtensions.yesno("Test", yesNo));
        //Number
        assertEquals(no, JavaExtensions.yesno(0L, yesNo));
        assertEquals(yes, JavaExtensions.yesno(1L, yesNo));
        assertEquals(yes, JavaExtensions.yesno(-1L, yesNo));
        //Collection
        List <String> testCollection = new ArrayList<>();
        assertEquals(no, JavaExtensions.yesno(testCollection, yesNo));
        testCollection.add("1");
        assertEquals(yes, JavaExtensions.yesno(testCollection, yesNo));
        // NullObject
        NullObject nullObject = NullObject.getNullObject();
        assertEquals(no, JavaExtensions.yesno(nullObject, yesNo));
    }

    @Test 
    public void testLast()  {
        List <String> testCollection = new ArrayList<>();
        testCollection.add("1");
        testCollection.add("2");
        assertEquals("2", JavaExtensions.last(testCollection));
    }

    @Test 
    public void testJoin()  {
        List <String> testCollection = new ArrayList<>();
        testCollection.add("1");
        testCollection.add("2");
        
        assertEquals("1, 2", JavaExtensions.join(testCollection, ", "));
    }

}
