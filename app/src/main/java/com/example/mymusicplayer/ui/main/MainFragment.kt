package com.example.mymusicplayer.ui.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.mymusicplayer.BaseFragment
import com.example.mymusicplayer.R
import com.example.mymusicplayer.aop.TraceDelay
import com.example.mymusicplayer.databinding.MainFragmentBinding
import java.io.DataInputStream
import java.io.IOException
import java.io.PrintWriter
import java.lang.Exception
import java.net.InetAddress
import java.net.Socket
import android.widget.Toast
import com.blankj.utilcode.util.FileUtils
import com.example.mymusicplayer.utils.FileReader
import java.net.DatagramPacket
import java.net.DatagramSocket


//主页
@TraceDelay
class MainFragment : BaseFragment<MainFragmentBinding, MainViewModel>() {


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun getContentViewId(): Int {
        return R.layout.main_fragment
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun bindViewModel() {
        mDataBinding.viewmodel = mViewModel
//        openZipDocument?.launch(null)
//        scanWifi()


        val stringArray = resources.getStringArray(R.array.china_weather_city_map_list)

        Log.e("TEST","RESULT = "+stringArray)
        Thread{
//            sendBroadCastToCenter()
//            scanEnthernet(getWifiHostIP())
        }.start()
    }

    val theSocket: DatagramSocket? = DatagramSocket(8888)

    @RequiresApi(Build.VERSION_CODES.M)
    fun sendBroadCastToCenter() {
        try {
            val server = InetAddress.getByName("255.255.255.255")
            val data = "Hello"
            val theOutput = DatagramPacket(data.toByteArray(), data.length, server, 8888)
            /*这一句就是发送广播了，其实255就代表所有的该网段的IP地址，是由路由器完成的工作*/
//            while (true){
                theSocket?.send(theOutput)

                Log.e("sender","sendBroadCastToCenter = ")
//            }

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (theSocket != null) theSocket.close()
        }
    }

    fun recevicerBrocastToCenter(){
        Thread{
            val ackBuf = ByteArray(1024)
//            val address = InetAddress.getByName("255.255.255.255")
//            val ackPacket = DatagramPacket(ackBuf, ackBuf.size, address, 8888)
        val packet = DatagramPacket(ackBuf, ackBuf.size)

            while (true){
                try {
                        theSocket?.receive(packet)
                    val resContent = String(packet.data, 0, packet.length)
                    val repsoneIp = packet.address.hostAddress
                    Log.e("recevicer","resContent = "+resContent)
                    Log.e("recevicer","repsoneIp = "+repsoneIp)
                }catch (e:IOException){
                    e.printStackTrace()
                }
        }
        }.start()
    }



    fun UDPDiscardSServer() {
//        byte[] buffer = new byte[1024];
//        /*在这里同样使用约定好的端口*/
//        int port = 8091;
//        DatagramSocket server = null;
//        try {
//            server = new DatagramSocket (port);
//            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
//            while(true){
//                try {
//                    server.receive(packet);
//                    String s = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
//                    System.out.println("address : " + packet.getAddress() + ", port : " + packet.getPort() + ", content : " + s);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }finally{
//            if(server != null)
//                server.close();
//        }
    }




    private fun scanEnthernet(ipaddr:String) {
        val runtime = Runtime.getRuntime()
        val ping = "ping -c 1 -w 0.5 "
        val exec = runtime.exec(ping + ipaddr)
        val result = exec.waitFor()
        if (result == 0){
            Log.e("scanEnthernet","connect success!")
        }

        // 向服务器发送验证信息
        val msg: String = sendMsg(
            "192.168.1.1", "scan" + ipaddr + " ( " + Build.MODEL.toString() + " ) "
        )
        //如果验证通过...

        if (msg != null){

            if (msg.contains("OK")){

                System.out.println("服务器IP：" + msg.substring(8,msg.length));

                Message.obtain(handler, 333, msg.substring(2,msg.length)).sendToTarget();//返回扫描完毕消息

            }

        }
    }


    private val handler: Handler = object : Handler() {
        override fun dispatchMessage(msg: Message) {
            when (msg.what) {
                222 -> {
                }
                333 -> Toast.makeText(
                    requireContext(),
                    "扫描到主机：" + (msg.obj as String).substring(6),
                    Toast.LENGTH_LONG
                ).show()
                444 -> Toast.makeText(requireContext(), msg.obj as String, Toast.LENGTH_LONG).show()
            }
        }
    }
    fun sendMsg(ip: String?, msg: String?): String {
         var res = ""
        var socket: Socket? = null
        try {
            socket = Socket(ip, 8888)

            //向服务器发送消息
            val os = PrintWriter(socket.getOutputStream())
            os.println(msg)
            os.flush() // 刷新输出流，使Server马上收到该字符串

            //从服务器获取返回消息
            val input = DataInputStream(socket.getInputStream())
            res = input.readUTF()
            Log.e("","server 返回信息：$res")
            Message.obtain(handler, 222, res).sendToTarget() //发送服务器返回消息
        } catch (unknownHost: Exception) {
            Log.e("","You are trying to connect to an unknown host!")
        } finally {

        // 4: Closing connection
            try {
                if (socket != null) {
                    socket.close()
                }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
            }
        }
        return res
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getWifiHostIP():String {
        val wifiManager = requireActivity().getSystemService(WifiManager::class.java)
        val dhcpInfo = wifiManager.dhcpInfo.toString()
        val ipaddr = dhcpInfo.substring(dhcpInfo.indexOf("ipaddr")+7,dhcpInfo.indexOf("gateway"))
        return ipaddr
    }

    private fun scanWifi() {
        val wifiManager = requireActivity().getSystemService(WifiManager::class.java)
        wifiScanReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
                if (success) {
                    val wifiList = wifiManager.scanResults.filter {
                        it.SSID.isNotEmpty()
                    }.map {
                        it.SSID
                    }.toMutableList()
                } else {
                }
                if (wifiScanReceiver != null) {
                    requireContext().unregisterReceiver(wifiScanReceiver)
                }
            }
        }
        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        requireContext().registerReceiver(wifiScanReceiver, intentFilter)

        val isEnable = wifiManager.startScan()
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onResume() {
        super.onResume()
        val bundle = Bundle()
        bundle.putString("tag","MainFragment")
        firebaseAnalytics.logEvent("currFragment", bundle)
        recevicerBrocastToCenter()
//        mDataBinding.radarview.setSpeedx2()
//        mDataBinding.radarview.startAnimationScan()

//        mDataBinding.radarview.insertData(intArrayOf(18,2,20,16,5,13,30))
    }

    private lateinit var wifiScanReceiver: BroadcastReceiver
    private val openZipDocument : ActivityResultLauncher<Uri>? = registerForActivityResult(
        ActivityResultContracts.OpenDocumentTree()){}

}