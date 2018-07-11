<?xml version="1.0" encoding="UTF-8"?>
<schema xmlns="http://purl.oclc.org/dsdl/schematron" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:u="utils"
        schemaVersion="iso" queryBinding="xslt2">

   <title>Sjekk mot norsk bokf.lov</title>

   <ns uri="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" prefix="cbc"/>
   <ns uri="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" prefix="cac"/>
   <ns uri="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2" prefix="ubl"/>
   <!-- [ph] added -->
   <ns uri="http://www.w3.org/2001/XMLSchema" prefix="xs"/>
   <ns uri="utils" prefix="u"/>

   <function xmlns="http://www.w3.org/1999/XSL/Transform" name="u:twodec">
     <param name="val"/>
     <value-of select="round($val * 100) div 100"/>
   </function>

   <function xmlns="http://www.w3.org/1999/XSL/Transform" name="u:slack" as="xs:boolean">
     <param name="exp"/>
     <param name="val"/>
     <param name="slack"/>
     <value-of select="$exp + xs:decimal($slack) &gt;= $val and $exp - xs:decimal($slack) &lt;= $val"/>
   </function>

   <function xmlns="http://www.w3.org/1999/XSL/Transform" name="u:cat2str">
     <param name="cat"/>
     <value-of select="concat(normalize-space($cat/cbc:ID), '-', round(xs:decimal($cat/cbc:Percent) * 1000000))"/>
   </function>

   <pattern>
      <let name="isZ01" value="/ubl:Invoice/cbc:InvoiceTypeCode = 'Z01'"/>
      <let name="isZ02" value="/ubl:Invoice/cbc:InvoiceTypeCode = 'Z02'"/>
      <let name="taxCategoryPercents" value="for $cat in /ubl:Invoice/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory return u:cat2str($cat)"/>
      <let name="taxCategories" value="for $cat in /ubl:Invoice/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory return normalize-space($cat/cbc:ID)"/>

      <rule context="ubl:Invoice">
         <assert id="NONAT-T10-R012"
                 test="cac:TaxTotal"
                 flag="fatal">[NONAT-T10-R012]-An invoice MUST contain tax information</assert>
         <assert id="NONAT-T10-R002"
                 test="cac:PaymentMeans/cbc:PaymentDueDate"
                 flag="fatal">[NONAT-T10-R002]-Payment due date MUST be provided in the invoice according to "FOR 2004-12-01 nr 1558 - § 5-1-1. Point 5"</assert>
         <assert id="NONAT-T10-R013"
                 test="not(cac:PayeeParty) or cac:PayeeParty/cac:PartyName/cbc:Name"
                 flag="fatal">[NONAT-T10-R013]-If payee information is provided then the payee name MUST be specified.</assert>
         <assert id="NONAT-T10-R009"
                 test="not(cbc:IssueDate) or current-date() &gt;= cbc:IssueDate"
                 flag="warning">[NONAT-T10-R009]-Issue date of an invoice should be today or earlier.</assert>
         <assert id="NONAT-T10-R003"
                 test="$isZ02 or //cac:Delivery/cbc:ActualDeliveryDate"
                 flag="warning">[NONAT-T10-R003]-The actual delivery date SHOULD be provided in the invoice according to "FOR 2004-12-01 nr 1558 - § 5-1-1. Point 4 and § 5-1-4", see also “NOU 2002:20, point 9.4.1.4”"</assert>
         <assert id="NONAT-T10-R004"
                 test="$isZ02 or //cac:Delivery/cac:DeliveryLocation/cac:Address/cbc:CityName and //cac:Delivery/cac:DeliveryLocation/cac:Address/cbc:PostalZone and //cac:Delivery/cac:DeliveryLocation/cac:Address/cac:Country/cbc:IdentificationCode"
                 flag="warning">[NONAT-T10-R004]-A Delivery address in an invoice SHOULD contain at least, city, zip code and country code according to "FOR 2004-12-01 nr 1558 - § 5-1-1. Point 4 and § 5-1-4", see also “NOU 2002:20, point 9.4.1.4”</assert>
      </rule>
      <rule context="cbc:UBLVersionID">
         <assert id="NONAT-T10-R020"
                 test="normalize-space(.) = '2.1'"
                 flag="fatal">[NONAT-T10-R020]-UBL version  must be 2.1</assert>
      </rule>
      <rule context="cac:AccountingSupplierParty/cac:Party">
         <assert id="NONAT-T10-R001"
                 test="$isZ02 or cac:PartyLegalEntity/cbc:CompanyID"
                 flag="fatal">[NONAT-T10-R001]-The Norwegian legal registration ID for the supplier MUST be provided according to "FOR 2004-12-01 nr 1558 - § 5-1-1. Point 2"</assert>
         <assert id="NONAT-T10-R008"
                 test="$isZ02 or cac:PartyLegalEntity/cbc:RegistrationName"
                 flag="fatal">[NONAT-T10-R008]-The Norwegian legal registration name for the supplier MUST be provided according to "FOR 2004-12-01 nr 1558 - § 5-1-1. Point 2"</assert>
         <assert id="NONAT-T10-R006"
                 test="cac:PostalAddress/cbc:CityName and cac:PostalAddress/cbc:PostalZone and cac:PostalAddress/cac:Country/cbc:IdentificationCode"
                 flag="fatal">[NONAT-T10-R006]-A supplier postal address in an invoice MUST contain at least city name, zip code and country code.</assert>
      </rule>
      <rule context="cac:AccountingCustomerParty/cac:Party">
         <assert id="NONAT-T10-R007"
                 test="cac:PostalAddress/cbc:CityName and cac:PostalAddress/cbc:PostalZone and cac:PostalAddress/cac:Country/cbc:IdentificationCode"
                 flag="fatal">[NONAT-T10-R007]-A customer postal address in an invoice MUST contain at least city name, zip code and country code.</assert>
      </rule>
      <rule context="cac:Delivery/cac:DeliveryLocation/cbc:ID[@schemeID]">
         <assert id="NONAT-T10-R010"
                 test="some $v in tokenize('GLN GSRN', '\s') satisfies $v = @schemeID"
                 flag="warning">[NONAT-T10-R010]-Location identifiers SHOULD be GLN or GSRN</assert>
      </rule>
      <rule context="cac:PartyLegalEntity">
         <assert id="NONAT-T10-R018"
                 test="cbc:CompanyID"
                 flag="fatal">[NONAT-T10-R018]-Company identifier MUST be specified when describing a company legal entity.</assert>
      </rule>
      <rule context="cac:PayeeFinancialAccount/cbc:ID[@schemeID]">
         <assert id="NONAT-T10-R024"
                 test="some $v in tokenize('IBAN BBAN LOCAL', '\s') satisfies $v = @schemeID"
                 flag="fatal">[NONAT-T10-R024]-A payee account identifier scheme MUST be either IBAN, BBAN or LOCAL</assert>
      </rule>
      <rule context="cac:LegalMonetaryTotal">
         <assert id="NONAT-T10-R023"
                 test="number(cbc:TaxInclusiveAmount) &gt;= 0"
                 flag="warning">[NONAT-T10-R023]-Tax inclusive amount in an invoice SHOULD NOT be negative</assert>
         <assert id="NONAT-T10-R022"
                 test="number(cbc:PayableAmount) &gt;= 0"
                 flag="warning">[NONAT-T10-R022]-Total payable amount in an invoice SHOULD NOT be negative</assert>
      </rule>
      <rule context="cac:AllowanceCharge">
         <assert id="NONAT-T10-R011"
                 test="cbc:AllowanceChargeReason"
                 flag="warning">[NONAT-T10-R011]-AllowanceChargeReason text SHOULD be specified for all allowances and charges</assert>
      </rule>
      <rule context="cac:InvoiceLine">
         <let name="sumCharge" value="sum(cac:AllowanceCharge[child::cbc:ChargeIndicator='true']/cbc:Amount)" />
         <let name="sumAllowance" value="sum(cac:AllowanceCharge[child::cbc:ChargeIndicator='false']/cbc:Amount)"/>
         <let name="baseQuantity" value="xs:decimal(if (cac:Price/cbc:BaseQuantity and xs:decimal(cac:Price/cbc:BaseQuantity) != 0) then cac:Price/cbc:BaseQuantity else 1)"/>
         <let name="pricePerUnit" value="xs:decimal(cac:Price/cbc:PriceAmount) div $baseQuantity"/>
         <let name="quantity" value="xs:decimal(cbc:InvoicedQuantity)"/>
         <let name="lineExtensionAmount" value="number(cbc:LineExtensionAmount)"/>
         <let name="quiet" value="not(cbc:InvoicedQuantity) or not(cac:Price/cbc:PriceAmount)"/>

         <assert id="NONAT-T10-R016"
                 test="cac:Item/cbc:Name"
                 flag="fatal">[NONAT-T10-R016]-Each invoice line MUST contain the product/service name</assert>
         <assert id="NONAT-T10-R015"
                 test="cac:Price/cbc:PriceAmount"
                 flag="fatal">[NONAT-T10-R015]-Invoice lines MUST contain the item price</assert>
         <assert id="NONAT-T10-R026"
                 test="$quiet or u:slack($lineExtensionAmount, u:twodec(u:twodec($pricePerUnit * $quantity) + u:twodec($sumCharge) - u:twodec($sumAllowance)), 0.02)"
                 flag="fatal">[NONAT-T10-R026]-Invoice line amount MUST be equal to the price amount multiplied by the quantity plus charges minus allowances at line level.</assert>
      </rule>

      <rule context="cac:Price/cbc:BaseQuantity">
        <assert id="NONAT-T10-R033"
                test="xs:decimal(.) &gt; 0"
                flag="fatal">[NONAT-T10-R033]-Base quantity must be a positive value higher than zero.</assert>
      </rule>

      <rule context="cac:TaxSubtotal">
         <let name="category" value="normalize-space(cac:TaxCategory/cbc:ID)"/>
         <let name="sumLineExtensionAmount" value="xs:decimal(sum(/ubl:Invoice/cac:InvoiceLine[normalize-space(cac:Item/cac:ClassifiedTaxCategory/cbc:ID) = $category]/cbc:LineExtensionAmount))"/>
         <let name="sumAllowance" value="xs:decimal(sum(/ubl:Invoice/cac:AllowanceCharge[normalize-space(cac:TaxCategory/cbc:ID) = $category][cbc:ChargeIndicator = 'false']/cbc:Amount))"/>
         <let name="sumCharge" value="xs:decimal(sum(/ubl:Invoice/cac:AllowanceCharge[normalize-space(cac:TaxCategory/cbc:ID) = $category][cbc:ChargeIndicator = 'true']/cbc:Amount))"/>

         <assert id="NONAT-T10-R029"
                 test="xs:decimal(cbc:TaxableAmount) = u:twodec($sumLineExtensionAmount - $sumAllowance + $sumCharge)"
                 flag="fatal">[NONAT-T10-R029]-Taxable amount in a tax subtotal MUST be the sum of line extension amount of all invoice lines and allowances and charges on document level with the same tax category.</assert>
      </rule>

      <!-- TAXATION -->

      <rule context="cac:AllowanceCharge/cac:TaxCategory[cbc:Percent] | cac:Item/cac:ClassifiedTaxCategory[cbc:Percent]">
        <let name="category" value="u:cat2str(.)"/>

        <assert id="NONAT-T10-R031"
                test="some $cat in $taxCategoryPercents satisfies $cat = $category"
                flag="fatal">[NONAT-T10-R031]-Tax categories MUST match provided tax categories on document level.</assert>
      </rule>
      <rule context="cac:AllowanceCharge/cac:TaxCategory | cac:Item/cac:ClassifiedTaxCategory">
        <assert id="NONAT-T10-R032"
                test="some $cat in $taxCategories satisfies $cat = cbc:ID"
                flag="fatal">[NONAT-T10-R032]-Tax categories MUST match provided tax categories on document level.</assert>
      </rule>
      <rule context="cac:TaxScheme">
         <assert id="NONAT-T10-R017"
                 test="cbc:ID"
                 flag="fatal">[NONAT-T10-R017]-Every tax scheme MUST be defined through an identifier.</assert>
      </rule>
      <rule context="cac:TaxScheme/cbc:ID">
         <assert id="NONAT-T10-R014"
                 test="normalize-space(.) = 'VAT'"
                 flag="fatal">[NONAT-T10-R014]-Invoice tax schemes MUST be 'VAT'</assert>
      </rule>

   </pattern>
</schema>
