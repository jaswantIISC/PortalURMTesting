//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.12.17 at 02:51:19 PM IST 
//


package cdot.ccsp.utils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import cdot.onem2m.resource.xsd.AnnouncedFlexContainerResource;
import cdot.onem2m.resource.xsd.ChildResourceRef;
import cdot.onem2m.resource.xsd.Subscription;


/**
 * <p>Java class for deviceThreeDPrinterAnnc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deviceThreeDPrinterAnnc">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.onem2m.org/xml/protocols}announcedFlexContainerResource">
 *       &lt;sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="childResource" type="{http://www.onem2m.org/xml/protocols}childResourceRef" maxOccurs="unbounded"/>
 *           &lt;choice maxOccurs="unbounded">
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}binarySwitch"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}binarySwitchAnnc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}faultDetection"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}faultDetectionAnnc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}threeDPrinter"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}threeDPrinterAnnc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}runState"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}runStateAnnc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}temperature"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}temperatureAnnc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}printQueue"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}printQueueAnnc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}subscription"/>
 *           &lt;/choice>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deviceThreeDPrinterAnnc", propOrder = {
    "childResource",
    "binarySwitchOrBinarySwitchAnncOrFaultDetection"
})
public class DeviceThreeDPrinterAnnc
    extends AnnouncedFlexContainerResource
{

    protected List<ChildResourceRef> childResource;
    @XmlElements({
        @XmlElement(name = "binarySwitch", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = BinarySwitch.class),
        @XmlElement(name = "binarySwitchAnnc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = BinarySwitchAnnc.class),
        @XmlElement(name = "faultDetection", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = FaultDetection.class),
        @XmlElement(name = "faultDetectionAnnc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = FaultDetectionAnnc.class),
        @XmlElement(name = "threeDPrinter", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = ThreeDPrinter.class),
        @XmlElement(name = "threeDPrinterAnnc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = ThreeDPrinterAnnc.class),
        @XmlElement(name = "runState", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = RunState.class),
        @XmlElement(name = "runStateAnnc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = RunStateAnnc.class),
        @XmlElement(name = "temperature", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = Temperature.class),
        @XmlElement(name = "temperatureAnnc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = TemperatureAnnc.class),
        @XmlElement(name = "printQueue", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = PrintQueue.class),
        @XmlElement(name = "printQueueAnnc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = PrintQueueAnnc.class),
        @XmlElement(name = "subscription", namespace = "http://www.onem2m.org/xml/protocols", type = Subscription.class)
    })
    protected List<Object> binarySwitchOrBinarySwitchAnncOrFaultDetection;

    /**
     * Gets the value of the childResource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the childResource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChildResource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChildResourceRef }
     * 
     * 
     */
    public List<ChildResourceRef> getChildResource() {
        if (childResource == null) {
            childResource = new ArrayList<ChildResourceRef>();
        }
        return this.childResource;
    }

    /**
     * Gets the value of the binarySwitchOrBinarySwitchAnncOrFaultDetection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the binarySwitchOrBinarySwitchAnncOrFaultDetection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBinarySwitchOrBinarySwitchAnncOrFaultDetection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BinarySwitch }
     * {@link BinarySwitchAnnc }
     * {@link FaultDetection }
     * {@link FaultDetectionAnnc }
     * {@link ThreeDPrinter }
     * {@link ThreeDPrinterAnnc }
     * {@link RunState }
     * {@link RunStateAnnc }
     * {@link Temperature }
     * {@link TemperatureAnnc }
     * {@link PrintQueue }
     * {@link PrintQueueAnnc }
     * {@link Subscription }
     * 
     * 
     */
    public List<Object> getBinarySwitchOrBinarySwitchAnncOrFaultDetection() {
        if (binarySwitchOrBinarySwitchAnncOrFaultDetection == null) {
            binarySwitchOrBinarySwitchAnncOrFaultDetection = new ArrayList<Object>();
        }
        return this.binarySwitchOrBinarySwitchAnncOrFaultDetection;
    }

}
