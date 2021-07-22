package com.chatApp.demo.seeders;

import com.chatApp.demo.model.Message;
import com.chatApp.demo.model.Room;
import com.chatApp.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

@Component
public class MessageSeed {
    private static final String jeremyLim91= "jeremylim_91" ;
    private static final String joshuaLim91= "joshualim_91" ;
    private static final String josephpok91= "josephpok_91" ;
    String devHangoutObjId;
    String foodiesUniteObjId;
    String cyclingBuddiesObjId;

    @Autowired private MessageRepository messageRepository;
    @Autowired private DocIds docIds;
    @Autowired private SeederStrings seederStrings;

//    @Override
//    public void run (String...args) throws Exception{

    public long count(){
        return messageRepository.count();
    }

        public List <Message> seedMessageData(List allRooms){
        //        Empty DB of any existing data related to users
        messageRepository.deleteAll();

        List <Message> allMessages= new ArrayList<Message>();
        

        allRooms.forEach(room->{
            Room roomInstance= (Room) room;
            if (roomInstance.getName().equals(seederStrings.devHangout)) devHangoutObjId= roomInstance.getId();
            if (roomInstance.getName().equals(seederStrings.foodiesUnite)) foodiesUniteObjId= roomInstance.getId();
            if (roomInstance.getName().equals(seederStrings.cyclingBuddies)) cyclingBuddiesObjId= roomInstance.getId();
        });

        //       Create messages based on Message model. Add each of them to a list
        allMessages.add(new Message("Hey bro how's it going", jeremyLim91, LocalDateTime.now().minusMinutes(100), devHangoutObjId));
        allMessages.add(new Message("Hey I'm good! how're your?", joshuaLim91, LocalDateTime.now().minusMinutes(90), devHangoutObjId));
        allMessages.add(new Message("Nth much going on.", jeremyLim91, LocalDateTime.now().minusMinutes(20), devHangoutObjId));
        allMessages.add( new Message("Just wanted to catchup with you on Dev work! What projects you working on lately?", jeremyLim91, LocalDateTime.now().minusMinutes(19), devHangoutObjId));

        allMessages.add(new Message("What time are we meeting and where?", josephpok91, LocalDateTime.now().minusMinutes(200), cyclingBuddiesObjId));
        allMessages.add(new Message("3am at the usual spot!", jeremyLim91, LocalDateTime.now().minusMinutes(186), cyclingBuddiesObjId));
        allMessages.add(new Message("okie cool thanks", josephpok91, LocalDateTime.now().minusMinutes(180), cyclingBuddiesObjId));
        allMessages.add(new Message("Sorry I'm new can I clarify where the 'usual spot' refers to?", joshuaLim91, LocalDateTime.now().minusMinutes(120), cyclingBuddiesObjId));
        allMessages.add(new Message("Kranji, by the coffee shop' refers to?", josephpok91, LocalDateTime.now().minusMinutes(118), cyclingBuddiesObjId));
        allMessages.add(new Message("Ok got it, thanks bro", joshuaLim91, LocalDateTime.now().minusMinutes(89), cyclingBuddiesObjId));

        //        Bulk insert the list of users
        messageRepository.insert(allMessages);
        return allMessages;
    }
}