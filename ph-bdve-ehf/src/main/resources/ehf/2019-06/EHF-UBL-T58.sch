<schema xmlns="http://purl.oclc.org/dsdl/schematron"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:xi="http://www.w3.org/2001/XInclude"
        xmlns:u="utils"
        schemaVersion="iso"
        queryBinding="xslt2">

   <title>Norwegian rules for EHF Catalogue Response</title>

   <ns uri="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"
       prefix="cbc"/>
   <ns uri="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2"
       prefix="cac"/>
   <ns uri="urn:oasis:names:specification:ubl:schema:xsd:ApplicationResponse-2"
       prefix="ubl"/>

   <pattern xmlns:ns2="http://www.schematron-quickfix.com/validator/process">
      <rule context="/ubl:ApplicationResponse">
         <assert test="cbc:UBLVersionID" flag="warning" id="EHF-T58-B00101">Element 'cbc:UBLVersionID' MUST be provided.</assert>
         <assert test="cbc:ID" flag="warning" id="EHF-T58-B00102">Element 'cbc:ID' MUST be provided.</assert>
         <assert test="cbc:IssueDate" flag="warning" id="EHF-T58-B00103">Element 'cbc:IssueDate' MUST be provided.</assert>
         <assert test="cac:SenderParty" flag="warning" id="EHF-T58-B00104">Element 'cac:SenderParty' MUST be provided.</assert>
         <assert test="cac:ReceiverParty" flag="warning" id="EHF-T58-B00105">Element 'cac:ReceiverParty' MUST be provided.</assert>
      </rule>
      <rule context="/ubl:ApplicationResponse/cbc:UBLVersionID">
         <assert test="normalize-space(text()) = '2.1'"
                 flag="warning"
                 id="EHF-T58-B00201">Element 'cbc:UBLVersionID' MUST contain value '2.1'.</assert>
      </rule>
      <rule context="/ubl:ApplicationResponse/cbc:CustomizationID"/>
      <rule context="/ubl:ApplicationResponse/cbc:ProfileID"/>
      <rule context="/ubl:ApplicationResponse/cbc:ID"/>
      <rule context="/ubl:ApplicationResponse/cbc:IssueDate"/>
      <rule context="/ubl:ApplicationResponse/cbc:IssueTime"/>
      <rule context="/ubl:ApplicationResponse/cbc:Note"/>
      <rule context="/ubl:ApplicationResponse/cac:SenderParty"/>
      <rule context="/ubl:ApplicationResponse/cac:SenderParty/cbc:EndpointID">
         <assert test="@schemeID" flag="warning" id="EHF-T58-B01101">Attribute 'schemeID' MUST be present.</assert>
      </rule>
      <rule context="/ubl:ApplicationResponse/cac:SenderParty/cac:PartyIdentification">
         <assert test="cbc:ID" flag="warning" id="EHF-T58-B01301">Element 'cbc:ID' MUST be provided.</assert>
      </rule>
      <rule context="/ubl:ApplicationResponse/cac:SenderParty/cac:PartyIdentification/cbc:ID"/>
      <rule context="/ubl:ApplicationResponse/cac:SenderParty/cac:PartyName">
         <assert test="cbc:Name" flag="warning" id="EHF-T58-B01601">Element 'cbc:Name' MUST be provided.</assert>
      </rule>
      <rule context="/ubl:ApplicationResponse/cac:SenderParty/cac:PartyName/cbc:Name"/>
      <rule context="/ubl:ApplicationResponse/cac:ReceiverParty"/>
      <rule context="/ubl:ApplicationResponse/cac:ReceiverParty/cbc:EndpointID">
         <assert test="@schemeID" flag="warning" id="EHF-T58-B01901">Attribute 'schemeID' MUST be present.</assert>
      </rule>
      <rule context="/ubl:ApplicationResponse/cac:ReceiverParty/cac:PartyIdentification">
         <assert test="cbc:ID" flag="warning" id="EHF-T58-B02101">Element 'cbc:ID' MUST be provided.</assert>
      </rule>
      <rule context="/ubl:ApplicationResponse/cac:ReceiverParty/cac:PartyIdentification/cbc:ID"/>
      <rule context="/ubl:ApplicationResponse/cac:ReceiverParty/cac:PartyName">
         <assert test="cbc:Name" flag="warning" id="EHF-T58-B02401">Element 'cbc:Name' MUST be provided.</assert>
      </rule>
      <rule context="/ubl:ApplicationResponse/cac:ReceiverParty/cac:PartyName/cbc:Name"/>
      <rule context="/ubl:ApplicationResponse/cac:DocumentResponse">
         <assert test="cac:Response" flag="warning" id="EHF-T58-B02601">Element 'cac:Response' MUST be provided.</assert>
         <assert test="cac:DocumentReference" flag="warning" id="EHF-T58-B02602">Element 'cac:DocumentReference' MUST be provided.</assert>
      </rule>
      <rule context="/ubl:ApplicationResponse/cac:DocumentResponse/cac:Response"/>
      <rule context="/ubl:ApplicationResponse/cac:DocumentResponse/cac:Response/cbc:ResponseCode"/>
      <rule context="/ubl:ApplicationResponse/cac:DocumentResponse/cac:DocumentReference">
         <assert test="cbc:ID" flag="warning" id="EHF-T58-B03001">Element 'cbc:ID' MUST be provided.</assert>
      </rule>
      <rule context="/ubl:ApplicationResponse/cac:DocumentResponse/cac:DocumentReference/cbc:ID"/>
      <rule context="/ubl:ApplicationResponse/cac:DocumentResponse/cac:DocumentReference/cbc:VersionID"/>
   </pattern>
   <pattern>
      <rule context="/ubl:ApplicationResponse">
         <assert id="NOGOV-T58-R003"
                 test="cac:SenderParty/cbc:EndpointID"
                 flag="warning">[NOGOV-T58-R003]-A catalogue response should have sellers endpoint id.</assert>
         <assert id="NOGOV-T58-R004"
                 test="cac:ReceiverParty/cbc:EndpointID"
                 flag="warning">[NOGOV-T58-R004]-A catalogue response should have the receivers endpoint id.</assert>
      </rule>
      <rule context="cbc:ProfileID">
         <assert id="EHFPROFILE-T58-R001"
                 test=". = 'urn:www.cenbii.eu:profile:bii01:ver2.0'"
                 flag="fatal">[EHFPROFILE-T58-R001]-A Catalogue response must only be used in profile 1</assert>
      </rule>
   </pattern>

</schema>
