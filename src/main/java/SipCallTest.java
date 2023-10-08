//import javax.sip.*;
//import javax.sip.address.*;
//import javax.sip.header.*;
//import javax.sip.message.*;
//
//public class SipCallTest {
//    private static final int YOUR_LOCAL_PORT_NUMBER = 666;
//
//    public static void makeSipCall(String destinationNumber) {
//        try {
//            SipFactory sipFactory = SipFactory.getInstance();
//            sipFactory.setPathName("gov.nist");
//
//            SipStack sipStack = sipFactory.createSipStack(System.getProperties());
//            SipProvider sipProvider = sipStack.createSipProvider(sipStack.createListeningPoint("YOUR_LOCAL_IP_ADDRESS", YOUR_LOCAL_PORT_NUMBER, "udp"));
//
//            SipURI fromUri = sipFactory.createAddressFactory().createSipURI("YOUR_SIP_USERNAME", "YOUR_SIP_DOMAIN");
//            Address fromAddress = sipFactory.createAddressFactory().createAddress(fromUri);
//            FromHeader fromHeader = sipFactory.createHeaderFactory().createFromHeader(fromAddress, "tag12345");
//
//            SipURI toUri = sipFactory.createAddressFactory().createSipURI(destinationNumber, "YOUR_SIP_SERVER");
//            Address toAddress = sipFactory.createAddressFactory().createAddress(toUri);
//            ToHeader toHeader = sipFactory.createHeaderFactory().createToHeader(toAddress, null);
//
//            CallIdHeader callIdHeader = sipProvider.getNewCallId();
//            CSeqHeader cSeqHeader = sipFactory.createHeaderFactory().createCSeqHeader(1L, Request.INVITE);
//
//            MaxForwardsHeader maxForwardsHeader = sipFactory.createHeaderFactory().createMaxForwardsHeader(70);
//            Request request = sipFactory.createMessageFactory().createRequest(toUri, Request.INVITE, callIdHeader, cSeqHeader, fromHeader, toHeader, null, maxForwardsHeader);
//
//            // Add custom headers if needed
//            Header contactHeader = sipFactory.createHeaderFactory().createContactHeader(fromAddress);
//            request.addHeader(contactHeader);
//
//            // Set body content if needed
//            String body = "Test message";
//            ContentTypeHeader contentTypeHeader = sipFactory.createHeaderFactory().createContentTypeHeader("text", "plain");
//            request.setContent(body, contentTypeHeader);
//
//            // Send the request
//            ClientTransaction clientTransaction = sipProvider.getNewClientTransaction(request);
//            clientTransaction.sendRequest();
//
//            // Wait for the response
//            Response response = clientTransaction.getResponse();
//            int statusCode = response.getStatusCode();
//
//            if (statusCode >= 200 && statusCode < 300) {
//                // Call has been successfully initiated
//                // Handle the call duration or any other necessary logic
//            } else {
//                // Call initiation failed
//                // Handle the failure scenario
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        String destinationNumber = "PHONE_NUMBER_TO_CALL";
//        makeSipCall(destinationNumber);
//    }
//}