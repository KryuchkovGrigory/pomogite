import javax.sip.*;
import javax.sip.address.*;
import javax.sip.header.*;
import javax.sip.message.*;
import java.util.*;

public class SipCallTester {
    private static final String SIP_SERVER = "sip.serverNO LINKS";
    private static final int SIP_PORT = 5060;
    private static final String SIP_USERNAME = "your_username";
    private static final String SIP_PASSWORD = "your_password";
    private static final String DESTINATION_NUMBER = "9999";
    private static final int NUM_CALLS = 5;

    public static void main(String[] args) {
        try {
            SipFactory sipFactory = SipFactory.getInstance();
            AddressFactory addressFactory = sipFactory.createAddressFactory();
            sipFactory.setPathName("gov.nist");

            Properties properties = new Properties();
            properties.setProperty("javax.sip.STACK_NAME", "sipStack");

            SipStack sipStack = sipFactory.createSipStack(properties);

            ListeningPoint listeningPoint = sipStack.createListeningPoint("your_local_ip", 5060, "udp");
            SipProvider sipProvider = sipStack.createSipProvider(listeningPoint);

            SipURI fromAddress = createSipURI(SIP_USERNAME, SIP_SERVER);
            Address fromNameAddress = addressFactory.createAddress(fromAddress);
            FromHeader fromHeader = sipFactory.createHeaderFactory().createFromHeader(fromNameAddress, "12345");

            SipURI toAddress = createSipURI(DESTINATION_NUMBER, SIP_SERVER);
            Address toNameAddress = addressFactory.createAddress(toAddress);
            ToHeader toHeader = sipFactory.createHeaderFactory().createToHeader(toNameAddress, null);

            for (int i = 0; i < NUM_CALLS; i++) {
                CallIdHeader callIdHeader = sipProvider.getNewCallId();

                CSeqHeader cSeqHeader = sipFactory.createHeaderFactory().createCSeqHeader(1L, Request.INVITE);

                MaxForwardsHeader maxForwardsHeader = sipFactory.createHeaderFactory().createMaxForwardsHeader(70);

                Request request = sipFactory.createMessageFactory().createRequest(
                        toAddress,
                        Request.INVITE,
                        callIdHeader,
                        cSeqHeader,
                        fromHeader,
                        toHeader,
                        Collections.singletonList(maxForwardsHeader),
                        null
                );

                ClientTransaction transaction = sipProvider.getNewClientTransaction(request);
                transaction.sendRequest();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SipURI createSipURI(String username, String server) {
        try {
            SipFactory sipFactory = SipFactory.getInstance();
            AddressFactory addressFactory = sipFactory.createAddressFactory();
            SipURI sipURI = addressFactory.createSipURI(username, server);
            sipURI.setTransportParam("udp");
            return sipURI;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}