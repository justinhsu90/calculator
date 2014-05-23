package app.utility.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vpadn.ads.VpadnAd;
import com.vpadn.ads.VpadnAdRequest;
import com.vpadn.ads.VpadnAdSize;
import com.vpadn.ads.VpadnBanner;

public class MainActivity extends Activity {
	static TextView output, formula = null;
	static Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
	static Button btn_C, btn_delete, btn_divided, btn_minus, btn_remainder, btn_times, btn_plus, btn_dot, btn_equal;
	double[] numArr;
	static String[] funArr = { "+", "-", "*", "Ö", "%" };
	static String outputInString;
	static String lastCha;
	private RelativeLayout adBannerLayout;
	private VpadnBanner vpadnBanner = null;
	private String bannerId = "8a80818245eed90c0145f6425dfa06c4";
	private boolean reEnter = false;
	private ArrayList<String> expression = new ArrayList<String>();

	private boolean equalPressed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		adBannerLayout = (RelativeLayout) findViewById(R.id.adLayout);
		vpadnBanner = new VpadnBanner(this, bannerId, VpadnAdSize.SMART_BANNER, "TW");
		VpadnAdRequest adRequest = new VpadnAdRequest();
		adRequest.setEnableAutoRefresh(true);
		HashSet<String> testDeviceImeiSet = new HashSet<String>();
		testDeviceImeiSet.add(getImei(this));
		// adRequest.setTestDevices(testDeviceImeiSet);
		adRequest.setGender(VpadnAdRequest.Gender.FEMALE);
		vpadnBanner.loadAd(adRequest);
		adBannerLayout.addView(vpadnBanner);
		initilized();
		SetMyOnClick();
	};

	public static String getImei(Context context) {
		String imei = "";
		try {
			TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			imei = telephonyManager.getDeviceId();
			return imei;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void initilized() {
		btn_0 = (Button) this.findViewById(R.id.Btn_0);
		btn_1 = (Button) this.findViewById(R.id.Btn_1);
		btn_2 = (Button) this.findViewById(R.id.Btn_2);
		btn_3 = (Button) this.findViewById(R.id.Btn_3);
		btn_4 = (Button) this.findViewById(R.id.Btn_4);
		btn_5 = (Button) this.findViewById(R.id.Btn_5);
		btn_6 = (Button) this.findViewById(R.id.Btn_6);
		btn_7 = (Button) this.findViewById(R.id.Btn_7);
		btn_8 = (Button) this.findViewById(R.id.Btn_8);
		btn_9 = (Button) this.findViewById(R.id.Btn_9);
		btn_C = (Button) this.findViewById(R.id.Btn_C);
		btn_delete = (Button) this.findViewById(R.id.Btn_delete);
		btn_divided = (Button) this.findViewById(R.id.Btn_divided);
		btn_minus = (Button) this.findViewById(R.id.Btn_minus);
		btn_dot = (Button) this.findViewById(R.id.Btn_dot);
		btn_plus = (Button) this.findViewById(R.id.Btn_plus);
		btn_times = (Button) this.findViewById(R.id.Btn_times);
		btn_remainder = (Button) this.findViewById(R.id.Btn_remainder);
		btn_equal = (Button) this.findViewById(R.id.Btn_equal);
		output = (TextView) this.findViewById(R.id.output);
		formula = (TextView) this.findViewById(R.id.formula);
	}

	private void SetMyOnClick() {
		btn_0.setOnClickListener(MyOnClick);
		btn_1.setOnClickListener(MyOnClick);
		btn_2.setOnClickListener(MyOnClick);
		btn_3.setOnClickListener(MyOnClick);
		btn_4.setOnClickListener(MyOnClick);
		btn_5.setOnClickListener(MyOnClick);
		btn_6.setOnClickListener(MyOnClick);
		btn_7.setOnClickListener(MyOnClick);
		btn_8.setOnClickListener(MyOnClick);
		btn_9.setOnClickListener(MyOnClick);
		btn_C.setOnClickListener(MyOnClick);
		btn_delete.setOnClickListener(MyOnClick);
		btn_divided.setOnClickListener(MyOnClick);
		btn_minus.setOnClickListener(MyOnClick);
		btn_remainder.setOnClickListener(MyOnClick);
		btn_times.setOnClickListener(MyOnClick);
		btn_plus.setOnClickListener(MyOnClick);
		btn_dot.setOnClickListener(MyOnClick);
		btn_equal.setOnClickListener(MyOnClick);
	}

	private Button.OnClickListener MyOnClick = new Button.OnClickListener() {

		public void onClick(View v) {
			// if (reEnter) {
			//
			// formula.setText(formula.getText() + "" + output.getText());
			// output.setText("");
			// reEnter = false;
			// }

			switch (v.getId()) {
			case R.id.Btn_0:
				inputCheck("0");
				break;
			case R.id.Btn_1:
				inputCheck("1");
				break;
			case R.id.Btn_2:
				inputCheck("2");
				break;
			case R.id.Btn_3:
				inputCheck("3");
				break;
			case R.id.Btn_4:
				inputCheck("4");
				break;
			case R.id.Btn_5:
				inputCheck("5");
				break;
			case R.id.Btn_6:
				inputCheck("6");
				break;
			case R.id.Btn_7:
				inputCheck("7");
				break;
			case R.id.Btn_8:
				inputCheck("8");
				break;
			case R.id.Btn_9:
				inputCheck("9");
				break;
			case R.id.Btn_dot:
				inputCheck(".");
				break;
			case R.id.Btn_plus:
				inputCheck("+");
				break; // 0=+
			case R.id.Btn_minus:
				inputCheck("-");
				break; // 1=-
			case R.id.Btn_times:
				inputCheck("*");
				break; // 2=X
			case R.id.Btn_divided:
				inputCheck("Ö");
				break; // 3=/
			case R.id.Btn_remainder:
				inputCheck("%");
				break; // 99=
			case R.id.Btn_C:
				output.setText("");
				formula.setText("");
				break;
			case R.id.Btn_delete:
				outputInString = outputInString.substring(0, outputInString.length() - 1);
				output.setText(outputInString);
				break;
			case R.id.Btn_equal:
				equalPressed = true;

				formula.setText(outputInString + "=");
				output.setText(calculate(outputInString));

			}
		}
	};

	public void setText(String input) {
		output.setText(output.getText() + input);
		outputInString = output.getText().toString();
		lastCha = outputInString.substring(outputInString.length() - 1);
		System.out.println("LASTCHA in setText is " + lastCha);
	}

	public void inputCheck(String input) {
		if (equalPressed) {
			outputInString = "";
			if (Arrays.asList(funArr).contains(input)) {
				String temp = expression.get(0);
				expression.clear();
				expression.add(temp);
				equalPressed = false;
			} else {
				expression.clear();
				formula.setText(formula.getText() + "" + output.getText());
				output.setText("");
				equalPressed = false;
			}
		}
		boolean has = false;
		if (output.getText().toString().length() > 0) {
			if (Arrays.asList(funArr).contains(input) || input.equals(".")) {
				if (!outputInString.contains("%") && (input.equals("+") || input.equals("-") || input.equals("Ö") || input.equals("*") || input.equals("."))) {
					if (!Arrays.asList(funArr).contains(lastCha) && !lastCha.equals(".")) {
						setText(input);
					}
				} else if (input.equals("%")) { // if input is %
					for (int i = 0; i < funArr.length; i++) {
						if (outputInString.contains(funArr[i])) {
							has = true;
							break;
						}
					}
					if (!has) { // if not exist
						setText(input);
						has = false;
					}
				}
			} else {// input is not a function
				setText(input);
			}
		} else {// length of output is 0
			if (!(input.equals("+") || input.equals("-") || input.equals("Ö") || input.equals("*") || input.equals(".") || input.equals("%"))) {
				setText(input);
			}
		}

	}

	private String calculate(String output) {
		if (equalPressed) {
			String tempString = "";
			System.out.println("string is:" + output);
			if (output != null) {
				for (int i = 0; i < output.length(); i++) {
					if (Arrays.asList(funArr).contains(String.valueOf(output.charAt(i)))) {
						expression.add(tempString);
						tempString = "";
						if (i != output.length() - 1) {
							expression.add(String.valueOf(output.charAt(i)));
						}
					} else {
						tempString = tempString + output.charAt(i);
						if (i == output.length() - 1) {
							expression.add(tempString);
						}
					}
				}
				if (!expression.contains("%") && expression.size() < 2) {
					int expressionIndex = 1;
					while (expression.contains("*") || expression.contains("Ö")) {
						if (expression.get(expressionIndex).equals("*")) {
							int indexOfFun = expression.indexOf("*");
							double fromParam = Double.parseDouble(expression.get(indexOfFun - 1));
							double endParam = Double.parseDouble(expression.get(indexOfFun + 1));
							double tempSum = fromParam * endParam;
							expression.remove(indexOfFun);
							expression.remove(indexOfFun);
							expression.remove(indexOfFun - 1);
							expression.add(indexOfFun - 1, String.valueOf(tempSum));
						} else if (expression.get(expressionIndex).equals("Ö")) {
							int indexOfFun = expression.indexOf("Ö");
							double fromParam = Double.parseDouble(expression.get(indexOfFun - 1));
							double endParam = Double.parseDouble(expression.get(indexOfFun + 1));
							double tempSum = fromParam / endParam;
							expression.remove(indexOfFun);
							expression.remove(indexOfFun);
							expression.remove(indexOfFun - 1);
							expression.add(indexOfFun - 1, String.valueOf(tempSum));
						}
						if (expressionIndex < expression.size() - 1 && !expression.get(expressionIndex).equals("*") && !expression.get(expressionIndex).equals("Ö")) {
							expressionIndex = expressionIndex + 2;
						}
					}
					while (expression.contains("+") || expression.contains("-")) {
						double fromParam = Double.parseDouble(expression.get(0));
						double endParam = Double.parseDouble(expression.get(2));
						double tempSum = 0;
						if (expression.get(1).equals("+")) {
							tempSum = fromParam + endParam;
						} else if (expression.get(1).equals("-")) {
							tempSum = fromParam - endParam;
						}
						expression.remove(0);
						expression.remove(0);
						expression.remove(0);
						expression.add(0, String.valueOf(tempSum));
					}
				} else if (expression.size() > 2) {
					System.out.println("expression size isisisisis:"+expression.size());
					double fromParam = Double.parseDouble(expression.get(0));
					double endParam = Double.parseDouble(expression.get(2));
					double remainder = fromParam % endParam;
					double tempSum = remainder;
					expression.clear();
					expression.add(String.valueOf(tempSum));
				} else 
				{

				System.out.println("TETETETETETE");
				}
			}
			if (output == null) {
				equalPressed = false;

				return "0";
			} else {
				equalPressed = false;

				Toast.makeText(this, String.valueOf(expression.size()), Toast.LENGTH_SHORT).show();
				if (Double.parseDouble(expression.get(0)) % 1 == 0) {
					reEnter = true;
					return String.valueOf((int) Double.parseDouble(expression.get(0)));
				} else {
					reEnter = true;
					return (String.valueOf(expression.get(0).toString()));
				}
			}
		}
		return null;
	}

	public interface VpadnAdListener {
		void onVpadnReceiveAd(VpadnAd ad);

		void onVpadnFailedToReceiveAd(VpadnAd ad, VpadnAdRequest.VpadnErrorCode errorCode);

		void onVpadnPresentScreen(VpadnAd ad);

		void onVpadnDismissScreen(VpadnAd ad);

		void onVpadnLeaveApplication(VpadnAd ad);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (vpadnBanner != null) {
			vpadnBanner.destroy();
			vpadnBanner = null;
		}
	}

}
