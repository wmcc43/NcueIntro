package android.edu.intro.network;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class client {
	private String ip = "192.168.1.11";	//欲連線的Server IP
    private int port = 8178;				//欲連線的Server Port
    private boolean connection;				//連線是否還存在(暫停使用)
    private Socket socket;
    private BufferedReader input;
    private BufferedWriter output;
    private String result;
    
    //註冊
    public boolean register(String account, String password, String email, String sex, String birth){
    	connect();
    	try{
    		// 送出註冊資料
            output.write("register\n");
            output.write(account+"\n");
            output.write(password+"\n");
            output.write(email+"\n");
            output.write(sex+"\n");
            output.write(birth+"\n");
            output.flush();
            result = input.readLine();
        }
        catch (java.io.IOException e){
            System.out.println("IOException :" + e.toString());
        }
        disconnect();
        if(result.equals("success"))
        	return true;
        else
        	return false;
    }
    //登入
    public boolean login(String account, String password){
    	connect();
    	try{
    		// 送出登入資料
            output.write("login\n");
            output.write(account+"\n");
            output.write(password+"\n");
            output.flush();
            result = input.readLine();
        }
        catch (java.io.IOException e){
            System.out.println("IOException :" + e.toString());
        }
        disconnect();
        if(result.equals("success"))
        	return true;
        else
        	return false;
    }
    
    
    
    
    //取得經驗值
    public int getExp(String account){
    	connect();
    	try{
    		output.write("getExp\n");
    		output.write(account+"\n");
            output.flush();
            result = input.readLine();
    	}
    	catch (java.io.IOException e){
            System.out.println("IOException :" + e.toString());
        }
    	disconnect();
    	return Integer.parseInt(result);
    }
    //更新經驗值
    public int updateExp(String account, int exp){
    	connect();
    	try{
    		output.write("updateExp\n");
    		output.write(account+"\n");
            output.write(exp+"\n");
            output.flush();
            
            result = input.readLine();
    	}
    	catch (java.io.IOException e){
            System.out.println("IOException :" + e.toString());
        }
    	disconnect();
    	return Integer.parseInt(result);
    }
    
    
    
    
    //取得遊戲分數
    public int getScore(String account, String game){
    	connect();
    	try{
    		output.write("getScore\n");
            output.write(account+"\n");
            output.write(game+"\n");
            output.flush();
    		result = input.readLine();
    	}
    	catch (java.io.IOException e){
            System.out.println("IOException :" + e.toString());
        }
    	disconnect();
    	return Integer.parseInt(result);
    }
    //更新遊戲分數
    public int updateScore(String account, String game, int score){
    	connect();
    	try{
    		output.write("updateScore\n");
            output.write(account+"\n");
            output.write(game+"\n");
            output.write(score+"\n");
            output.flush();
    		result = input.readLine();
    	}
    	catch (java.io.IOException e){
            System.out.println("IOException :" + e.toString());
        }
    	disconnect();
    	return Integer.parseInt(result);
    }
    //取得遊戲分數排行
    public String[][] getTopScore(String game, int start, int end){
    	connect();
    	String[][] rank = new String[end-start+1][2];
    	try{
    		output.write("getTopScore\n");
    		output.write(game+"\n");
            output.write(start+"\n");
            output.write(end+"\n");
            output.flush();
            
            int read_time = Integer.parseInt(input.readLine());
            
            for(int i=0; i<read_time; i++){
            	rank[i][0] = input.readLine();
            	rank[i][1] = input.readLine();
			}
    	}
    	catch (java.io.IOException e){
            System.out.println("IOException :" + e.toString());
        }
    	disconnect();
    	return rank;
    }
    
    
    
    //======================================================================
	//============================BY GAME UID===============================
	//======================================================================
    //取得遊戲分數(By Game UID)
    public int getScoreByGID(String account, int g_uid){
    	connect();
    	try{
    		output.write("getScoreByGID\n");
            output.write(account+"\n");
            output.write(g_uid+"\n");
            output.flush();
    		result = input.readLine();
    	}
    	catch (java.io.IOException e){
            System.out.println("IOException :" + e.toString());
        }
    	disconnect();
    	return Integer.parseInt(result);
    }
    //更新遊戲分數(By Game UID)
    public int updateScoreByGID(String account, int g_uid, int score){
    	connect();
    	try{
    		output.write("updateScoreByGID\n");
            output.write(account+"\n");
            output.write(g_uid+"\n");
            output.write(score+"\n");
            output.flush();
    		result = input.readLine();
    	}
    	catch (java.io.IOException e){
            System.out.println("IOException :" + e.toString());
        }
    	disconnect();
    	return Integer.parseInt(result);
    }
    //取得遊戲分數排行(By Game UID)
    public String[][] getTopScoreByGID(int g_uid, int start, int end){
    	connect();
    	String[][] rank = new String[end-start+1][2];
    	try{
    		output.write("getTopScoreByGID\n");
    		output.write(g_uid+"\n");
            output.write(start+"\n");
            output.write(end+"\n");
            output.flush();
            
            int read_time = Integer.parseInt(input.readLine());
            
            for(int i=0; i<read_time; i++){
            	rank[i][0] = input.readLine();
            	rank[i][1] = input.readLine();
			}
    	}
    	catch (java.io.IOException e){
            System.out.println("IOException :" + e.toString());
        }
    	disconnect();
    	return rank;
    }
    //======================================================================
	//============================BY GAME UID===============================
	//======================================================================
    
    
    
    
    
    
    
    //更新破關
	public int updateFinGame(String account, int g_uid){
		connect();
    	try{
    		output.write("updateFinGame\n");
    		output.write(account+"\n");
    		output.write(g_uid+"\n");
            output.flush();
            
            result = input.readLine();
    	}
    	catch (java.io.IOException e){
            System.out.println("IOException :" + e.toString());
        }
    	disconnect();
    	return Integer.parseInt(result);
	}
    //取得破關數
    public int getFinGameCount(String account){
    	connect();
    	try{
    		output.write("getFinGameCount\n");
    		output.write(account+"\n");
            output.flush();
            
            result = input.readLine();
    	}
    	catch (java.io.IOException e){
            System.out.println("IOException :" + e.toString());
        }
    	disconnect();
    	return Integer.parseInt(result);
    }
    //取得破關資料
    public int[] getFinGame(String account){
    	int read_time = getFinGameCount(account);
    	int[] fin_game = new int[read_time];
    	connect();
    	try{
    		output.write("getFinGame\n");
    		output.write(account+"\n");
            output.flush();
            
            for(int i=0; i<read_time; i++)
            	fin_game[i] = Integer.parseInt(input.readLine());
    	}
    	catch (java.io.IOException e){
            System.out.println("IOException :" + e.toString());
        }
    	disconnect();
    	return fin_game;
    }
    
    
    
    
    
    //建立連線
    private void connect(){
    	try{
	    	socket = new Socket(ip, port);
	        input = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
	        output = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
	        connection = true;
	        result = "fail";
    	}
    	catch (java.io.IOException e){
            System.out.println("IOException :" + e.toString());
        }
    }
    //結束連線
    private void disconnect(){
    	try{
	    	output.write("over\n");
	        output.flush();
	        output.close();
	        output = null;
	        socket.close();
	        socket = null;
	        connection = false;
    	}catch (java.io.IOException e){
    		System.out.println("IOException :" + e.toString());
        }
    }
    public boolean getConnection()
    {
    	//test code
    	return connection;
    }
}