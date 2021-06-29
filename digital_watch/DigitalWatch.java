package practice.digital_watch;

import javax.swing.*;
import java.awt.*;

public class DigitalWatch extends JFrame {
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;

    private boolean setHours = false;
    private boolean setMinutes = false;

    JLabel labelHours = new JLabel(String.format("%02d", this.hours));
    JLabel colon = new JLabel(":");
    JLabel labelMinutes = new JLabel(String.format("%02d", this.minutes));
    JLabel labelSeconds = new JLabel(String.format("%02d", this.seconds));

    Timer timer = new Timer(1000,
            (event) -> { timeElapses(); }
    );

    public DigitalWatch() {
        timer.start();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel south = new JPanel();
        JButton buttonA = new JButton("A");

        buttonA.addActionListener(
                (event) -> { buttonAClicked(); }
        );
        south.add(buttonA);
        JButton buttonB = new JButton("B");

        buttonB.addActionListener(
                (event) -> { buttonBClicked(); }
        );
        south.add(buttonB);

        JPanel center = new JPanel();
        center.add(labelHours);
        labelHours.setFont(new Font("Verdana", Font.PLAIN, 18));
        center.add(colon);
        center.add(labelMinutes);
        labelMinutes.setFont(new Font("Verdana", Font.PLAIN, 18));
        center.add(labelSeconds);
        labelSeconds.setFont(new Font("Verdana", Font.PLAIN, 10));

        this.getContentPane().add(BorderLayout.SOUTH, south);
        this.getContentPane().add(BorderLayout.CENTER, center);
        this.pack();
    }

    private void timeElapses() {
        this.seconds++;

        if(this.seconds==60) {
            this.seconds = 0;
            this.minutes++;
        }

        if(this.minutes==60) {
            this.minutes = 0;
            this.hours++;
        }

        if(this.hours==24) {
            this.hours = 0;
        }

        labelHours.setText(String.format("%02d", this.hours));
        labelMinutes.setText(String.format("%02d", this.minutes));
        labelSeconds.setText(String.format("%02d", this.seconds));

    }


    private void buttonAClicked() {
        if (timer.isRunning()) {
            timer.stop();
            setHours = true;
            labelHours.setForeground(Color.red);
        }
        else if (setHours){
            setHours = false;
            setMinutes = true;
            labelHours.setForeground(Color.black);
            labelMinutes.setForeground(Color.red);
        }
        else if (setMinutes == true)
        {
            labelMinutes.setForeground(Color.black);
            setMinutes = false;
            timer.start();
        }
    }

    private void buttonBClicked() {
        if(setHours) {
            if(this.hours==23) {
                this.hours = 0;
            }
            else {
                this.hours++;
            }
            labelHours.setText(String.format("%02d", this.hours));
        }
        else if(setMinutes) {
            if(this.minutes==59) {
                this.minutes = 0;
            }
            else {
                this.minutes++;
            }
            labelMinutes.setText(String.format("%02d", this.minutes));
        }
    }

    public static void main(String[] args) {
        DigitalWatch watch = new DigitalWatch();
        watch.setVisible(true);
    }
}