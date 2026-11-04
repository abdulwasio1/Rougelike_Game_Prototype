package org.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DungeonGraph {
    Map<Integer , ArrayList<Integer>> connection;

    public DungeonGraph() {
        connection = new  HashMap<>();
    }

    public void addRoom(int roomId){
        if (!connection.containsKey(roomId)){
            connection.put(roomId, new ArrayList<>());
        }
    }

    public void connectRooms(int currentRoom , int nextRoom){
        //auto check above whether it exists or not
        addRoom(currentRoom);
        addRoom(nextRoom);

        if (!connection.get(currentRoom).contains(nextRoom)){ //connection.get(room1) return arraylist of room1 and if it contains id of room 2 then it will not add
            connection.get(currentRoom).add(nextRoom); //connection.get(room1) return arraylist of room1 and .add() will add room2 id into connection arraylist of room1
        }
//        if(!connection.get(room2).contains(room1)){
//            connection.get(room2).add(room1);
//        }
    }
    public boolean isContain(int roomId){
        return connection.containsKey(roomId);
    }
    public ArrayList<Integer> getConnection(int roomID) {
        //if connection of above room id does not exist then it will create new arrayList
        if (!connection.containsKey(roomID)) {
            return new ArrayList<>();
        }
        return connection.get(roomID);
    }

}
