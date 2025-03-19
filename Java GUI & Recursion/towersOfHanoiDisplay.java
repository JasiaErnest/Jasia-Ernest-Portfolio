import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import javax.swing.*;

public class towersOfHanoiDisplay {

    // Helper function to move the disks
    public static void move(int n, ArrayList<Integer> src, ArrayList<Integer> dest, ArrayList<Integer> count, ArrayList<Integer> aux) {
        int lastIdx = src.size() - 1;
        int val = src.get(lastIdx);
        System.out.println("Move disk " + val + " from " + src + " to " + dest);
        dest.add(val);
        src.remove(lastIdx);
        count.add(1);
    }

    // Main recursive function to solve the puzzle
    public static void stack(int n, ArrayList<Integer> src, ArrayList<Integer> aux, ArrayList<Integer> dest, ArrayList<Integer> count) {  
        if (n == 0) {
            return;
        }
        stack(n - 1, src, dest, aux, count);
        move(n, src, dest, count, aux);
        stack(n - 1, aux, src, dest, count);
    }

    public static JFrame initializeFrame(ArrayList<Integer> src, ArrayList<Integer> aux, ArrayList<Integer> dest) {
        JFrame frame = new JFrame("Towers of Hanoi");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(750, 500);
        frame.setVisible(true);
        frame.setLayout(null);
        JLabel srcLabel = new JLabel("Source: " + src);
        srcLabel.setBounds(50, 50, 500, 50);
        frame.add(srcLabel);
        JLabel auxLabel = new JLabel("Auxiliary: " + aux);
        auxLabel.setBounds(50, 100, 500, 50);
        frame.add(auxLabel);
        JLabel destLabel = new JLabel("Destination: " + dest);
        destLabel.setBounds(50, 150, 500, 50);
        frame.add(destLabel);
        return frame;
    }

    public static void updateFrame(ArrayList<Integer> src, ArrayList<Integer> aux, ArrayList<Integer> dest) {
        JFrame frame = initializeFrame(src, aux, dest);
        CountDownLatch latch = new CountDownLatch(1);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                latch.countDown();
            }
        });
        try {
            latch.await(); // Wait until the frame is closed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of disks: ");
        int diskNum = scanner.nextInt();
        ArrayList<Integer> src = new ArrayList<>();
        ArrayList<Integer> aux = new ArrayList<>();
        ArrayList<Integer> dest = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();

        // Creates the source stack
        for (int i = 0; i < diskNum; i++) {
            src.add(diskNum - i);
        }
        // Initializes the frame
        System.out.println("Initial state:");
        updateFrame(src, aux, dest);
        System.out.println("The moves are:");
        // Calls the stack function to solve the puzzle
        stack(src.size(), src, aux, dest, count);
        // Prints the total number of moves
        System.out.println("Total number of moves: " + count.size());
        System.out.println("Final state:");
        updateFrame(src, aux, dest);
        scanner.close();
    }
}