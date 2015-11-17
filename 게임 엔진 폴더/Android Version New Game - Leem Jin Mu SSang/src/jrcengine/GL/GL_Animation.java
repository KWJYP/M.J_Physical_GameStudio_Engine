package jrcengine.GL;

import pangpang.MainGameLeemJinMuSSang.Screen_MainGame;

/*
 * ��������Ʈ �̹��� ��¿� ���ȴ�.
 * ���δ� ���� �Ƚ� ���� ���� �Ƚ� ��尡�ִ�
 */

public class GL_Animation {
	public static final int ANIMATION_LOOPING = 0;
	public static final int ANIMATION_NONLOOPING = 1;

	protected final GL_TextureRegion[] keyFrames;
	protected final float frameDuration;

	private int frameNumber;
	private int nFrameNumber;
	private float fPreviouseTime;
	private boolean isJustOne;

	private GL_TextureRegion firstFrame;

	public GL_Animation(GL_TextureRegion glFirstFrame, float frameDuration,
			GL_TextureRegion... keyFrames) {
		firstFrame = glFirstFrame;
		this.frameDuration = frameDuration;
		this.keyFrames = keyFrames;
		this.nFrameNumber = 0;
		this.fPreviouseTime = 0f;
		this.isJustOne = false;
	}

	// ���� ����̳� �ƴϳİ� �޷��ִ�.
	public GL_TextureRegion getKeyFrame(float stateTime, int mode) {
		frameNumber = (int) (stateTime / frameDuration);

		if (mode == ANIMATION_NONLOOPING) {
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);
		} else {
			frameNumber = frameNumber % keyFrames.length;
		}
		return keyFrames[frameNumber];
	}

	public GL_TextureRegion getKeyFrame(float StateTime) {

		if (isJustOne == false) {
			fPreviouseTime += StateTime;
			if (fPreviouseTime > frameDuration) {
				fPreviouseTime = 0;
				nFrameNumber++;

				if (nFrameNumber >= keyFrames.length) {
					nFrameNumber = 0;
					isJustOne = true;
					Screen_MainGame.iSmotionEnd = true;
				}

			}

			return keyFrames[nFrameNumber];
		} else
			return firstFrame;
	}

	public GL_TextureRegion getKeyFrame(float chrecterfPreviousTime,
			float StateTime) {

		if (isJustOne == false) {
			nFrameNumber = (int) (chrecterfPreviousTime / frameDuration);

			if (nFrameNumber >= keyFrames.length) {
				nFrameNumber = keyFrames.length-1;
				isJustOne = true;
			}

			return keyFrames[nFrameNumber];

		} else
			return firstFrame;
	}

	public void intNframeNumber() {
		nFrameNumber = 0;
		isJustOne = false;
	}

	public boolean getIsMaxFrameNum() {
		return isJustOne;
	}

	public int CurrentFrameNum() {
		return this.frameNumber;
	}

	public int EndKeyFrameNum() {
		return this.keyFrames.length - 1;
	}
}
