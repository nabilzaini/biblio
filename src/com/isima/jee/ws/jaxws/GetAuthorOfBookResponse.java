
package com.isima.jee.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAuthorOfBookResponse", namespace = "http://ws.jee.isima.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAuthorOfBookResponse", namespace = "http://ws.jee.isima.com/")
public class GetAuthorOfBookResponse {

    @XmlElement(name = "return", namespace = "")
    private com.isima.jee.models.Author _return;

    /**
     * 
     * @return
     *     returns Author
     */
    public com.isima.jee.models.Author getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.isima.jee.models.Author _return) {
        this._return = _return;
    }

}
