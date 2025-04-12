package Day5.PracticeProblems;
import java.util.ArrayList;

class FriendNetwork {
    private UserNode head;

    public void addUser(int userId, String name, int age) {
        UserNode newUser = new UserNode(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            UserNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newUser;
        }
    }

    public void addFriendConnection(int userId1, int userId2) {
        UserNode user1 = findUser(userId1);
        UserNode user2 = findUser(userId2);

        if (user1 != null && user2 != null) {
            user1.addFriend(userId2);
            user2.addFriend(userId1);
            System.out.println("Friend connection added between " + user1.name + " and " + user2.name);
        } else {
            System.out.println("One or both users not found!");
        }
    }

    public void removeFriendConnection(int userId1, int userId2) {
        UserNode user1 = findUser(userId1);
        UserNode user2 = findUser(userId2);

        if (user1 != null && user2 != null) {
            user1.removeFriend(userId2);
            user2.removeFriend(userId1);
            System.out.println("Friend connection removed between " + user1.name + " and " + user2.name);
        } else {
            System.out.println("One or both users not found!");
        }
    }

    public void findMutualFriends(int userId1, int userId2) {
        UserNode user1 = findUser(userId1);
        UserNode user2 = findUser(userId2);

        if (user1 != null && user2 != null) {
            System.out.print("Mutual Friends: ");
            for (int id1 : user1.friendIds) {
                for (int id2 : user2.friendIds) {
                    if (id1 == id2) {
                        UserNode mutual = findUser(id1);
                        if (mutual != null) {
                            System.out.print(mutual.name + " ");
                        }
                    }
                }
            }
            System.out.println();
        } else {
            System.out.println("One or both users not found!");
        }
    }

    public void displayFriends(int userId) {
        UserNode user = findUser(userId);
        if (user != null) {
            System.out.println(user.name + "'s Friends:");
            for (int fid : user.friendIds) {
                UserNode friend = findUser(fid);
                if (friend != null) {
                    System.out.println("- " + friend.name + " (ID: " + friend.userId + ")");
                }
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public void searchUserByName(String name) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                System.out.println("User Found: ID = " + temp.userId + ", Age = " + temp.age);
                return;
            }
            temp = temp.next;
        }
        System.out.println("User with name " + name + " not found.");
    }

    public void searchUserById(int userId) {
        UserNode user = findUser(userId);
        if (user != null) {
            System.out.println("User Found: Name = " + user.name + ", Age = " + user.age);
        } else {
            System.out.println("User with ID " + userId + " not found.");
        }
    }

    public void displayFriendCounts() {
        UserNode temp = head;
        while (temp != null) {
            System.out.println(temp.name + " has " + temp.friendIds.size() + " friends.");
            temp = temp.next;
        }
    }

    private UserNode findUser(int userId) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.userId == userId) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
}

// Node Class
class UserNode {
    int userId;
    String name;
    int age;
    ArrayList<Integer> friendIds;
    UserNode next;

    public UserNode(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
        this.next = null;
    }

    public void addFriend(int friendId) {
        if (!friendIds.contains(friendId)) {
            friendIds.add(friendId);
        }
    }

    public void removeFriend(int friendId) {
        friendIds.remove(Integer.valueOf(friendId));
    }
}
public class SocialMediaFriendConnections {
    public static void main(String[] args) {
        FriendNetwork network = new FriendNetwork();

        network.addUser(1, "Alice", 25);
        network.addUser(2, "Bob", 22);
        network.addUser(3, "Charlie", 24);
        network.addUser(4, "Diana", 23);

        network.addFriendConnection(1, 2);
        network.addFriendConnection(1, 3);
        network.addFriendConnection(2, 3);
        network.addFriendConnection(3, 4);

        System.out.println("Friends of Alice:");
        network.displayFriends(1);

        System.out.println("\nMutual friends between Alice and Bob:");
        network.findMutualFriends(1, 2);

        System.out.println("\nRemoving friend connection between Alice and Charlie:");
        network.removeFriendConnection(1, 3);
        network.displayFriends(1);

        System.out.println("\nSearching for User by Name (Diana):");
        network.searchUserByName("Diana");

        System.out.println("\nSearching for User by ID (3):");
        network.searchUserById(3);

        System.out.println("\nFriend Counts:");
        network.displayFriendCounts();
    }
}
