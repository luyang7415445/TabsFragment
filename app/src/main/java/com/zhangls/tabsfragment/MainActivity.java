package com.zhangls.tabsfragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;

import fragment.FragmentFactory;

/**
 * 主界面
 */
public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity);

        fragmentManager = getFragmentManager();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg_tab);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = FragmentFactory.getInstanceByIndex(checkedId);
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }
    //get local ip 需要网络权限
    //172.18.195.33
	public void scanNetworkInterface() {
		try {
			netinterface.clear();
			Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
			for(NetworkInterface netint : Collections.list(nets))
			{
				Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
				for (InetAddress inetAddress : Collections.list(inetAddresses)){
					StringTokenizer st = new StringTokenizer(inetAddress.getHostAddress(),".");
					if(st.countTokens() == 4) {
						if(!inetAddress.getHostAddress().equals("127.0.0.1")) {
					        netinterface.add(inetAddress);
						}
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}
