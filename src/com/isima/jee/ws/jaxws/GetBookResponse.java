
package com.isima.jee.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getBookResponse", namespace = "http://ws.jee.isima.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBookResponse", namespace = "http://ws.jee.isima.com/")
public class GetBookResponse {

    @XmlElement(name = "return", namespace = "")
    private com.isima.jee.models.Book _return;

    /**
     * 
     * @return
     *     returns Book
     */
    public com.isima.jee.models.Book getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.isima.jee.models.Book _return) {
        this._return = _return;
    }

}
