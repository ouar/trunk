<#if pojo.hasMetaAttribute("class-code")>  

// The following is extra code specified in the hbm.xml files   ${pojo.getExtraClassCode()}
 <#if pojo.getExtraClassCode() == "toString">
public String toString() {
StringBuffer buffer = new StringBuffer();
buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
<#foreach property in pojo.getAllPropertiesIterator()>      buffer.append("${property.getName()}").append("='").append(${pojo.getGetterSignature(property)}()).append("' ");			
</#foreach>     
 buffer.append("]");
  return buffer.toString();
}
 </#if>
</#if>
