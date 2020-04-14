package emailsender.inbox;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.util.Arrays;
import java.util.Properties;

public class AutomaticAnswer {
    public static void main( String[] args ) throws Exception {

        Session session = Session.getDefaultInstance(new Properties( ));
        Store store = session.getStore("imaps");
        store.connect("imap.timeweb.ru", 993, "dav@pkp96.ru", "boening_747");
        Folder inbox = store.getFolder( "INBOX" );
        inbox.open( Folder.READ_ONLY );

        // Fetch unseen messages from inbox folder
        Message[] messages = inbox.search(
                new FlagTerm(new Flags(Flags.Flag.SEEN), false));

        // Sort messages from recent to oldest
        Arrays.sort( messages, (m1, m2 ) -> {
            try {
                return m2.getSentDate().compareTo( m1.getSentDate() );
            } catch ( MessagingException e ) {
                throw new RuntimeException( e );
            }
        } );

        for ( Message message : messages ) {
            System.out.println(
                    "sendDate: " + message.getSentDate()
                            + " subject:" + message.getSubject()+" " );
        }
    }

}

/**
 * resources: https://overcoder.net/q/496803/javamail-%D1%87%D0%B8%D1%82%D0%B0%D0%B5%D1%82-%D0%BF%D0%BE%D1%81%D0%BB%D0%B5%D0%B4%D0%BD%D0%B8%D0%B5-%D0%BD%D0%B5%D0%BF%D1%80%D0%BE%D1%87%D0%B8%D1%82%D0%B0%D0%BD%D0%BD%D1%8B%D0%B5-%D0%BF%D0%B8%D1%81%D1%8C%D0%BC%D0%B0-%D1%81-%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%D0%BC-imap
 */
