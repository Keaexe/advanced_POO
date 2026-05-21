package UserInterface;

public class FloatingThread extends Thread{
    MonkPanel monkPanel;
    public FloatingThread(MonkPanel monkPanel){
        this.monkPanel = monkPanel;
    }
    public void run(){
        Integer y = 1;
        Integer i = 0;
        while (true){
            monkPanel.setLocation(monkPanel.getX(), monkPanel.getY() - y);
            if (i == 30){
                i = 0;
                y *= -1;
            }
            try {
                sleep(60);
            } catch (InterruptedException e) {
                System.out.println("Monk animation has encountered an error"); // TO MODIFY
                break;
            }
            i ++;
        }
    }
}
