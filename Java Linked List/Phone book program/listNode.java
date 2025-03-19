public class listNode<E> {
    protected E firstName;
    protected E lastName;
    protected E address;
    protected E phoneNum;
    listNode<E> nextNode;

    listNode() {
        this.nextNode = null;
    }

    listNode(listNode<E> incomingNode, E incomingFirstName, E incomingLastName, E incomingAddress, E incomingPhoneNum) {
       this.firstName = incomingFirstName;
       this.lastName = incomingLastName;
       this.address = incomingAddress;
       this.phoneNum = incomingPhoneNum;
       this.nextNode = incomingNode;
    }

    E getFirstName() {
        return this.firstName;    
    }

    E getLastName() {
        return this.lastName;    
    }

    E getAddress() {
        return this.address;    
    }
    E getPhoneNum() {
        return this.phoneNum;    
    }

    listNode<E> getNextNode() {
        return this.nextNode;
    }

}
