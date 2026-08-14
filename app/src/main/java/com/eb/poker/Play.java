package com.eb.poker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class Play extends Activity {
	
	private PokerEngine pe = new PokerEngine();
	private int bet = 1;
	private int win = 0;
	private int resetView = -1;
	private int resetColor = -1;
	private Button card1;
	private Button card2;
	private Button card3;
	private Button card4;
	private Button card5;
	private Button betOne;
	private Button betMax;
	private Button dealButton;
	private Button drawButton;
	private TextView betResult;
	private TextView winResult;
	private TextView scoreResult;
	private TextView creditResult;
	private TextView highPairTextView;
	private TextView twoPairTextView;
	private TextView threeOfaKindTextView;
	private TextView straightTextView;
	private TextView flushTextView;
	private TextView fullHouseTextView;
	private TextView fourOfaKindTextView;
	private TextView straightFlushTextView;
	private TextView royalFlushTextView;
	private boolean firstLoad = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.play);
		TableLayout tableLayout = (TableLayout) findViewById(R.id.TableLayout01);
		tableLayout.setStretchAllColumns(true);
		card1 = (Button) findViewById(R.id.Button01);
		card2 = (Button) findViewById(R.id.Button02);
		card3 = (Button) findViewById(R.id.Button03);
		card4 = (Button) findViewById(R.id.Button04);
		card5 = (Button) findViewById(R.id.Button05);
		highPairTextView = (TextView) findViewById(R.id.TextViewHighPair);
		twoPairTextView = (TextView) findViewById(R.id.TextView2Pair);
		threeOfaKindTextView = (TextView) findViewById(R.id.TextView3OfAKind);
		straightTextView = (TextView) findViewById(R.id.TextViewStraight);
		flushTextView = (TextView) findViewById(R.id.TextViewFlush);
		fullHouseTextView = (TextView) findViewById(R.id.TextViewFullHouse);
		fourOfaKindTextView = (TextView) findViewById(R.id.TextView4OfAKind);
		straightFlushTextView = (TextView) findViewById(R.id.TextViewStraightFlush);
		royalFlushTextView = (TextView) findViewById(R.id.TextViewRoyalFlush);
		betOne = (Button) findViewById(R.id.ButtonBetOne);
		betMax = (Button) findViewById(R.id.ButtonBetMax);
		winResult = (TextView) findViewById(R.id.TextViewWinRes);
		betResult = (TextView) findViewById(R.id.TextViewBetRes);
		scoreResult = (TextView) findViewById(R.id.TextViewScoreRes);
		creditResult = (TextView) findViewById(R.id.TextViewCredit);
		dealButton = (Button) this.findViewById(R.id.ButtonDeal);
		drawButton = (Button) this.findViewById(R.id.ButtonDraw);
		betResult.setText(new Integer(bet).toString());
		winResult.setText(new Integer(win).toString());
		creditResult.setText(new Long(pe.getCredit()).toString());
		scoreResult.setText(new Long(pe.getScore()).toString());
		setupButton();
		drawButton.setEnabled(false);
		pe.initDeck();
	}

	private void setupButton() {
		
		drawButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				processCardDealing();
				scoreResult.setText(new Long(pe.getScore()).toString());
				
				if (pe.getCredit() < bet) {
					if (pe.getCredit() == 0) {
						//Game over
						drawButton.setEnabled(false);
						dealButton.setEnabled(false);
						betMax.setEnabled(false);
						betOne.setEnabled(false);
					} else {
						drawButton.setEnabled(false);
						dealButton.setEnabled(true);
						betMax.setEnabled(true);
						betOne.setEnabled(true);
						bet = (int) pe.getCredit();
						betResult.setText(new Integer(bet).toString());
					}
				} else {
					drawButton.setEnabled(false);
					dealButton.setEnabled(true);
					betMax.setEnabled(true);
					betOne.setEnabled(true);
				}
			}
		});
		
		dealButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				processCardDealing();
				drawButton.setEnabled(true);
				dealButton.setEnabled(false);
				betMax.setEnabled(false);
				betOne.setEnabled(false);
			}
		});
		
		betMax.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if (!pe.isFirstDeal()) {
					bet = 5;
					betResult.setText("5");
				}
			}
		});
		
		betOne.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if (!pe.isFirstDeal()) {
					if (bet < 5) {
						bet++;
					} else if (bet == 5) {
						bet = 1;
					}
					betResult.setText(new Integer(bet).toString());
				}
			}
		});

		card1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if (pe.isFirstDeal()) {
					if (pe.getCardState()[0]) {
						card1.setText(R.string.empty_string);
						pe.getCardState()[0] = false;
					} else {
						card1.setText(R.string.keep);
						pe.getCardState()[0] = true;
					}
				}
			}
		});

		card2.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if (pe.isFirstDeal()) {
					if (pe.getCardState()[1]) {
						card2.setText(R.string.empty_string);
						pe.getCardState()[1] = false;
					} else {
						card2.setText(R.string.keep);
						pe.getCardState()[1] = true;
					}
				}
			}
		});

		card3.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if (pe.isFirstDeal()) {
					if (pe.getCardState()[2]) {
						card3.setText(R.string.empty_string);
						pe.getCardState()[2] = false;
					} else {
						card3.setText(R.string.keep);
						pe.getCardState()[2] = true;
					}
				}
			}
		});

		card4.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if (pe.isFirstDeal()) {
					if (pe.getCardState()[3]) {
						card4.setText(R.string.empty_string);
						pe.getCardState()[3] = false;
					} else {
						card4.setText(R.string.keep);
						pe.getCardState()[3] = true;
					}
				}
			}
		});

		card5.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if (pe.isFirstDeal()) {
					if (pe.getCardState()[4]) {
						card5.setText(R.string.empty_string);
						pe.getCardState()[4] = false;
					} else {
						card5.setText(R.string.keep);
						pe.getCardState()[4] = true;
					}
				}
			}
		});
	}
	
	private void processCardDealing() {
		if (!firstLoad) {
			if(pe.isFirstDeal()) {
				pe.setFirstDeal(false);
			} else {
				pe.initDeck();
				pe.setFirstDeal(true);
			}
		} else {
			firstLoad = false;
			pe.setFirstDeal(true);
		}
		resetKeptCards();
		int[] hand;
		if(pe.isFirstDeal()) {
			hand = pe.deal(bet);
		} else {
			hand = pe.deal();
		}
		card1.setBackgroundResource(hand[0]);
		card2.setBackgroundResource(hand[1]);
		card3.setBackgroundResource(hand[2]);
		card4.setBackgroundResource(hand[3]);
		card5.setBackgroundResource(hand[4]);
		if (!pe.isFirstDeal()) {
			int result = pe.checkResults();
			creditResult.setText(new Long(pe.getCredit()).toString());
			winResult.setText(new Integer(pe.getWin()).toString());
			checkHandResult(result);
			checkHandWinResult(pe.getWin());
		} else {
			resetHandResult();
			resetdisplayedResult();
			creditResult.setText(new Long(pe.getCredit()).toString());
			winResult.setText("0");
		}
	}
	
	private void resetHandResult() {
		highPairTextView.setTextColor(getResources().getColor(R.color.light_blue));
		twoPairTextView.setTextColor(getResources().getColor(R.color.light_blue));
		threeOfaKindTextView.setTextColor(getResources().getColor(R.color.light_blue));
		straightTextView.setTextColor(getResources().getColor(R.color.light_blue));
		flushTextView.setTextColor(getResources().getColor(R.color.light_blue));
		fullHouseTextView.setTextColor(getResources().getColor(R.color.light_blue));
		fourOfaKindTextView.setTextColor(getResources().getColor(R.color.light_blue));
		straightFlushTextView.setTextColor(getResources().getColor(R.color.light_blue));
		royalFlushTextView.setTextColor(getResources().getColor(R.color.light_blue));
		highPairTextView.setBackgroundDrawable(null);
		twoPairTextView.setBackgroundDrawable(null);
		threeOfaKindTextView.setBackgroundDrawable(null);
		straightTextView.setBackgroundDrawable(null);
		flushTextView.setBackgroundDrawable(null);
		fullHouseTextView.setBackgroundDrawable(null);
		fourOfaKindTextView.setBackgroundDrawable(null);
		straightFlushTextView.setBackgroundDrawable(null);
		royalFlushTextView.setBackgroundDrawable(null);
	}
	
	private void resetKeptCards() {
		card1.setText(R.string.empty_string);
		card2.setText(R.string.empty_string);
		card3.setText(R.string.empty_string);
		card4.setText(R.string.empty_string);
		card5.setText(R.string.empty_string);
	}
	
	private void displayResults(int textView, int color) {
		resetView = textView;
		resetColor = color;
		TextView hw = (TextView) findViewById(textView);
		hw.setTextColor(getResources().getColor(color));
		hw.setBackgroundColor(getResources().getColor(R.color.yellow));
	}
	
	private void resetdisplayedResult() {
		if (resetView != -1) {
			TextView hw = (TextView) findViewById(resetView);
			hw.setTextColor(getResources().getColor(R.color.yellow));
			hw.setBackgroundColor(getResources().getColor(resetColor));
			resetView = -1;
		}
	}
	
	private void checkHandResult(int result) {
		switch (result) {
		case R.string.pair:
			highPairTextView.setTextColor(getResources().getColor(R.color.yellow));
			highPairTextView.setBackgroundColor(getResources().getColor(R.color.red_result_3));
			break;
		case R.string.two_pair:
			twoPairTextView.setTextColor(getResources().getColor(R.color.yellow));
			twoPairTextView.setBackgroundColor(getResources().getColor(R.color.red_result_3));
			break;
		case R.string.three_of_a_kind:
			threeOfaKindTextView.setTextColor(getResources().getColor(R.color.yellow));
			threeOfaKindTextView.setBackgroundColor(getResources().getColor(R.color.red_result_3));
			break;
		case R.string.straight:
			straightTextView.setTextColor(getResources().getColor(R.color.yellow));
			straightTextView.setBackgroundColor(getResources().getColor(R.color.red_result_3));
			break;
		case R.string.flush:
			flushTextView.setTextColor(getResources().getColor(R.color.yellow));
			flushTextView.setBackgroundColor(getResources().getColor(R.color.red_result_3));
			break;
		case R.string.full_house:
			fullHouseTextView.setTextColor(getResources().getColor(R.color.yellow));
			fullHouseTextView.setBackgroundColor(getResources().getColor(R.color.red_result_3));
			break;
		case R.string.four_of_a_kind:
			fourOfaKindTextView.setTextColor(getResources().getColor(R.color.yellow));
			fourOfaKindTextView.setBackgroundColor(getResources().getColor(R.color.red_result_3));
			break;
		case R.string.straight_flush:
			straightFlushTextView.setTextColor(getResources().getColor(R.color.yellow));
			straightFlushTextView.setBackgroundColor(getResources().getColor(R.color.red_result_3));
			break;
		case R.string.royal_flush:
			royalFlushTextView.setTextColor(getResources().getColor(R.color.yellow));
			royalFlushTextView.setBackgroundColor(getResources().getColor(R.color.red_result_3));
			break;
		}
	}
	
	private void checkHandWinResult(int win) {
		int idx = win / bet;
		switch (idx) {
		case 1:
			if (bet == 1) {
				displayResults(R.id.TextView50, R.color.red_result_1);
			} else if (bet == 2) {
				displayResults(R.id.TextView51, R.color.red_result_2);
			} else if (bet == 3) {
				displayResults(R.id.TextView52, R.color.red_result_3);
			} else if (bet == 4) {
				displayResults(R.id.TextView53, R.color.red_result_4);
			} else {
				displayResults(R.id.TextView54, R.color.red_result_5);
			}
			break;
		case 2:
			if (bet == 1) {
				displayResults(R.id.TextView45, R.color.red_result_1);
			} else if (bet == 2) {
				displayResults(R.id.TextView46, R.color.red_result_2);
			} else if (bet == 3) {
				displayResults(R.id.TextView47, R.color.red_result_3);
			} else if (bet == 4) {
				displayResults(R.id.TextView48, R.color.red_result_4);
			} else {
				displayResults(R.id.TextView49, R.color.red_result_5);
			}
			break;
		case 3:
			if (bet == 1) {
				displayResults(R.id.TextView40, R.color.red_result_1);
			} else if (bet == 2) {
				displayResults(R.id.TextView41, R.color.red_result_2);
			} else if (bet == 3) {
				displayResults(R.id.TextView42, R.color.red_result_3);
			} else if (bet == 4) {
				displayResults(R.id.TextView43, R.color.red_result_4);
			} else {
				displayResults(R.id.TextView44, R.color.red_result_5);
			}
			break;
		case 4:
			if (bet == 1) {
				displayResults(R.id.TextView35, R.color.red_result_1);
			} else if (bet == 2) {
				displayResults(R.id.TextView36, R.color.red_result_2);
			} else if (bet == 3) {
				displayResults(R.id.TextView37, R.color.red_result_3);
			} else if (bet == 4) {
				displayResults(R.id.TextView38, R.color.red_result_4);
			} else {
				displayResults(R.id.TextView39, R.color.red_result_5);
			}
			break;
		case 6:
			if (bet == 1) {
				displayResults(R.id.TextView30, R.color.red_result_1);
			} else if (bet == 2) {
				displayResults(R.id.TextView31, R.color.red_result_2);
			} else if (bet == 3) {
				displayResults(R.id.TextView32, R.color.red_result_3);
			} else if (bet == 4) {
				displayResults(R.id.TextView33, R.color.red_result_4);
			} else {
				displayResults(R.id.TextView34, R.color.red_result_5);
			}
			break;
		case 9:
			if (bet == 1) {
				displayResults(R.id.TextView25, R.color.red_result_1);
			} else if (bet == 2) {
				displayResults(R.id.TextView26, R.color.red_result_2);
			} else if (bet == 3) {
				displayResults(R.id.TextView27, R.color.red_result_3);
			} else if (bet == 4) {
				displayResults(R.id.TextView28, R.color.red_result_4);
			} else {
				displayResults(R.id.TextView29, R.color.red_result_5);
			}
			break;
		case 25:
			if (bet == 1) {
				displayResults(R.id.TextView20, R.color.red_result_1);
			} else if (bet == 2) {
				displayResults(R.id.TextView21, R.color.red_result_2);
			} else if (bet == 3) {
				displayResults(R.id.TextView22, R.color.red_result_3);
			} else if (bet == 4) {
				displayResults(R.id.TextView23, R.color.red_result_4);
			} else {
				displayResults(R.id.TextView24, R.color.red_result_5);
			}
			break;
		case 50:
			if (bet == 1) {
				displayResults(R.id.TextView15, R.color.red_result_1);
			} else if (bet == 2) {
				displayResults(R.id.TextView16, R.color.red_result_2);
			} else if (bet == 3) {
				displayResults(R.id.TextView17, R.color.red_result_3);
			} else if (bet == 4) {
				displayResults(R.id.TextView18, R.color.red_result_4);
			} else {
				displayResults(R.id.TextView19, R.color.red_result_5);
			}
			break;
		case 250:
			if (bet == 1) {
				displayResults(R.id.TextView10, R.color.red_result_1);
			} else if (bet == 2) {
				displayResults(R.id.TextView11, R.color.red_result_2);
			} else if (bet == 3) {
				displayResults(R.id.TextView12, R.color.red_result_3);
			} else if (bet == 4) {
				displayResults(R.id.TextView13, R.color.red_result_4);
			} else {
				displayResults(R.id.TextView14, R.color.red_result_5);
			}
			break;
		}
	}
}
