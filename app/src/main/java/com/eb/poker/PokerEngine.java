package com.eb.poker;

public class PokerEngine {

	private boolean firstDeal = false;
	private long score = 0;
	
	public long getScore() {
		return score;
	}

	private boolean deck[] = new boolean[52];
	private int hand[] = new int[5];
	private boolean cardState[] = new boolean[] {false, false, false, false, false};
	private int value[] = new int[5];
	private int suit[] = new int[5];
	private int curMes = 0;
	private long credit = 20;
	private int bet = 0;
	private int win = 0;
	
	int payOut[] = {0, 1, 2, 3, 25, 9, 6, 4, 50, 250, 0};
	int mes[] = { R.string.empty_string, R.string.pair, R.string.two_pair,
			R.string.three_of_a_kind, R.string.four_of_a_kind,
			R.string.full_house, R.string.flush, R.string.straight,
			R.string.straight_flush, R.string.royal_flush, R.string.you_lose };

	int[] cardIDs = { 
			R.drawable.a2c, R.drawable.a3c, R.drawable.a4c, R.drawable.a5c, R.drawable.a6c, R.drawable.a7c, R.drawable.a8c,
			R.drawable.a9c, R.drawable.tc, R.drawable.jc, R.drawable.qc, R.drawable.kc, R.drawable.ac, 
			R.drawable.a2s, R.drawable.a3s, R.drawable.a4s, R.drawable.a5s, R.drawable.a6s, R.drawable.a7s, R.drawable.a8s,
			R.drawable.a9s, R.drawable.ts, R.drawable.js, R.drawable.qs, R.drawable.ks, R.drawable.as, 
			R.drawable.a2h, R.drawable.a3h, R.drawable.a4h, R.drawable.a5h, R.drawable.a6h, R.drawable.a7h, R.drawable.a8h, 
			R.drawable.a9h, R.drawable.th, R.drawable.jh, R.drawable.qh, R.drawable.kh, R.drawable.ah,
			R.drawable.a2d, R.drawable.a3d, R.drawable.a4d, R.drawable.a5d, R.drawable.a6d, R.drawable.a7d, R.drawable.a8d, 
			R.drawable.a9d, R.drawable.td, R.drawable.jd, R.drawable.qd, R.drawable.kd, R.drawable.ad };


	public void initDeck() {
		for (int i = 0; i < 52; i++) {
			deck[i] = false;
		}
		cardState = new boolean[] { false, false, false, false, false };
	}

	public int[] deal(int bet) {
		this.bet = bet;
		credit = credit - this.bet;
		return deal();
	}

	public int[] deal() {
		int i, card;
		for (i = 0; i < 5; i++) {
			if (firstDeal || !cardState[i]) {
				while (true) {
					card = (int) (Math.random() * 52);
					if (!deck[card]) {
						break;
					}
				}
				/*hand[0] = R.drawable.a2c;
				hand[1] = R.drawable.a2d;
				hand[2] = R.drawable.a2h;
				hand[3] = R.drawable.a2s;
				hand[4] = R.drawable.tc;*/
				
				hand[i] = cardIDs[card];
				deck[card] = true;
			}
		}
		return hand;
	}
	
	public int checkResults() {
		int i, tempory, j, threeVal;
		boolean flush, pair, three, straight, high, royal, four, two;
		flush = pair = three = two = straight = high = royal = four = false;
		threeVal = 15;

		// Break cards into values and suits
		for (i = 0; i < 5; i++) {
			for (int k = 0; k < cardIDs.length; k++) {
				if (hand[i] == cardIDs[k]) {
					if (k <= 13) {
						suit[i] = 0;
						value[i] = k;
					} else if (k > 13 && k <= 25) {
						suit[i] = 1;
						value[i] = k - 13;
					} else if (k > 25 && k <= 38) {
						suit[i] = 2;
						value[i] = k - 26;
					} else {
						suit[i] = 3;
						value[i] = k - 39;
					}
				}
			}
		}

		// Sort Cards
		for (i = 0; i < 4; i++)
			for (j = 1; j < 5 - i; j++) {
				if (value[j - 1] > value[j]) {
					tempory = value[j - 1];
					value[j - 1] = value[j];
					value[j] = tempory;
				}
			}

		// Check for Flush
		if ((suit[0] == suit[1]) && (suit[0] == suit[2]) && (suit[0] == suit[3]) && (suit[0] == suit[4])) {
			flush = true;
		}

		// Check for straight
		straight = true;
		for (i = 1; i < 5; i++) {
			if (value[i] != value[i - 1] + 1) {
				straight = false;
				break;
			}
		}
		if (straight) {
			if (value[0] > 7){
				royal = true;
			}
	}
		// Check for 4 of a kind
		if (((value[0] == value[1]) && (value[0] == value[2]) && (value[0] == value[3])) || ((value[1] == value[2]) && (value[1] == value[3]) && (value[1] == value[4]))) {
			four = true;
	}

		if (!four) {
			// Check for 3 of a kind
			for (i = 2; i < 5; i++) {
				if ((value[i - 2] == value[i - 1]) && (value[i - 1] == value[i])) {
					three = true;
					threeVal = value[i];
				}
			}

			// Check for pairs
			for (i = 1; i < 5; i++) {
				if ((value[i - 1] == value[i]) && (value[i] != threeVal)) {
					if (value[i] > 8) {
						high = true;
					}
					if (pair) {
						two = true;
					} else {
						pair = true;
					}
				}
			}
		}

		if (four) {
			curMes = 4;
		} else if ((pair) && (three)) {
			curMes = 5;
		} else if (two) {
			curMes = 2;
		} else if (three) {
			curMes = 3;
		} else if ((pair) && (high)) {
			curMes = 1;
		} else if ((straight) && (flush) && (royal)) {
			curMes = 9;
		} else if ((straight) && (flush)) {
			curMes = 8;
		} else if (straight) {
			curMes = 7;
		} else if (flush) {
			curMes = 6;
		} else {
			curMes = 10;
		}
		win = (this.bet * payOut[curMes]);
		score += win;
		credit = credit + win;
		return mes[curMes];
	}

	public int getWin() {
		return win;
	}

	public long getCredit() {
		return credit;
	}

	public void setCredit(long credit) {
		this.credit = credit;
	}
	
	public boolean isFirstDeal() {
		return firstDeal;
	}

	public void setFirstDeal(boolean firstDeal) {
		this.firstDeal = firstDeal;
	}

	public boolean[] getCardState() {
		return cardState;
	}
}
