<?xml version="1.0" encoding="UTF-8"?>
<!--This file is generated automatically! Do NOT edit!-->
<!--Schematron tests for binding UBL and transaction T14-->
<pattern xmlns="http://purl.oclc.org/dsdl/schematron" is-a="T14" id="UBL-T14">
  <param name="ATGOV-T14-R001" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and (cac:Contact/cbc:ElectronicMail)) or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT'))" />
  <param name="ATGOV-T14-R002" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and count(cac:CreditNoteLine) &lt; 999) or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT'))" />
  <param name="ATGOV-T14-R003" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and (cac:OrderReference/cbc:ID)) or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT'))" />
  <param name="ATGOV-T14-R004" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and (cbc:Note) and cbc:SettlementDiscountPercent and cac:SettlementPeriod) or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and (cbc:Note))" />
  <param name="ATGOV-T14-R005" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and count(cac:PaymentMeans/cac:PayeeFinancialAccount/cbc:ID) = 1) or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT'))" />
  <param name="ATGOV-T14-R006" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and (cbc:AccountingCost)) or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT'))" />
  <param name="ATGOV-T14-R007" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and (cbc:PaymentMeansCode = '30' or cbc:PaymentMeansCode = '31' or cbc:PaymentMeansCode = '42') and  (cac:PayeeFinancialAccount/cbc:ID/@schemeID and cac:PayeeFinancialAccount/cbc:ID/@schemeID = 'IBAN') and  (cac:PayeeFinancialAccount/cac:FinancialInstitutionBranch/cac:FinancialInstitution/cbc:ID/@schemeID and cac:PayeeFinancialAccount/cac:FinancialInstitutionBranch/cac:FinancialInstitution/cbc:ID/@schemeID = 'BIC')) or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT'))" />
  <param name="ATGOV-T14-R008" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and (string(number(//cac:OrderReference/cbc:ID)) != 'NaN') and (cac:OrderReferenceLine/cbc:LineID)) or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and (string(number(//cac:OrderReference/cbc:ID)) != 'NaN'))" />
  <param name="ATGOV-T14-R009" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and count(cac:PaymentTerms/cac:SettlementPeriod) &lt;= 2) or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT'))" />
  <param name="ATGOV-T14-R010" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and (cbc:SettlementDiscountPercent) and number(cbc:SettlementDiscountPercent) > 0 and number(cbc:SettlementDiscountPercent) &lt; 100) or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and (cbc:SettlementDiscountPercent))" />
  <param name="ATGOV-T14-R011" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and count(cac:Attachment/cac:ExternalReference) = 0) or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT'))" />
  <param name="ATGOV-T14-R012" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and (cac:Attachment/cbc:EmbeddedDocumentBinaryObject) and cac:Attachment/cbc:EmbeddedDocumentBinaryObject/@mimeCode = 'application/vnd.ms-excel' or cac:Attachment/cbc:EmbeddedDocumentBinaryObject/@mimeCode = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' or cac:Attachment/cbc:EmbeddedDocumentBinaryObject/@mimeCode = 'application/pdf' or cac:Attachment/cbc:EmbeddedDocumentBinaryObject/@mimeCode = 'image/png' or cac:Attachment/cbc:EmbeddedDocumentBinaryObject/@mimeCode = 'application/xml' or cac:Attachment/cbc:EmbeddedDocumentBinaryObject/@mimeCode = 'text/xml') or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and (cac:Attachment/cbc:EmbeddedDocumentBinaryObject))" />
  <param name="ATGOV-T14-R013" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and count(cac:AdditionalDocumentReference/cac:Attachment/cbc:EmbeddedDocumentBinaryObject) &lt;= 200) or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT'))" />
  <param name="ATGOV-T14-R014" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and string-length(string-join(cac:AdditionalDocumentReference/cac:Attachment/cbc:EmbeddedDocumentBinaryObject/text(),'')) * 3 div 4 &lt;= 15728640) or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT'))" />
  <param name="ATGOV-T14-R015" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and (number(cbc:LineExtensionAmount) >= -999999999999.99 and number(cbc:LineExtensionAmount) &lt;= 999999999999.99)) or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT'))" />
  <param name="ATGOV-T14-R016" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and number(cac:LegalMonetaryTotal/cbc:PayableAmount) &lt;= 999999999.99) or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT'))" />
  <param name="ATGOV-T14-R017" value="((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT') and count(cac:PaymentMeans/cac:PayeeFinancialAccount/cbc:ID) >= 1) or not ((//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode = 'AT'))" />
  <param name="CreditNote" value="/ubl:CreditNote" />
  <param name="Payment_Means" value="/ubl:CreditNote/cac:PaymentMeans" />
  <param name="Supplier" value="/ubl:CreditNote/cac:AccountingSupplierParty/cac:Party" />
  <param name="CreditNoteLine" value="/ubl:CreditNote/cac:CreditNoteLine" />
  <param name="Payment_Terms" value="/ubl:CreditNote/cac:PaymentTerms" />
  <param name="Attachments" value="/ubl:CreditNote/cac:AdditionalDocumentReference" />
</pattern>