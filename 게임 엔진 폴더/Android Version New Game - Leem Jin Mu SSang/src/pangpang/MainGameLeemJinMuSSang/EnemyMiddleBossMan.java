package pangpang.MainGameLeemJinMuSSang;

import java.util.Random;

import jrcengine.Manage.Manage_Assets;
import jrcengine.Math.Math_Overlap_Rectangle;

public class EnemyMiddleBossMan extends Enemy {

	public static final float ENEMY_WIDTH = 240f;
	public static final float ENEMY_SWORDMAN_WIDTH = 80f;
	public static final float ENEMY_SWORDMAN_HEIGHT = 100f;
	public static final float ENEMY_SWORDMAN_VELOCITY = 60f;

	public static final int TYPE01 = 1;
	public static final int TYPE02 = 2;

	private final float GAPHPBAR = 20f;
	private final float nDelaySwingTime = 1f;
	public Math_Overlap_Rectangle attackBounds;
	public float fCurrentSwingTime;
	private final int SWINGPERSENTAGE = 7;
	private Random rnd;
	private int type;

	public EnemyMiddleBossMan(float center_x, float center_y, int _type) {
		super(center_x, center_y, ENEMY_SWORDMAN_WIDTH, ENEMY_SWORDMAN_HEIGHT,
				100);
		this.setStateFlag(EnemyMiddleBossMan.OBJ_D_LEFT);

		this.rnd = new Random();
		this.fCurrentSwingTime = 0f;
		this.attackBounds = new Math_Overlap_Rectangle(center_x
				- ENEMY_SWORDMAN_WIDTH / 2, center_y - ENEMY_SWORDMAN_HEIGHT
				/ 2, ENEMY_SWORDMAN_WIDTH, ENEMY_SWORDMAN_HEIGHT);
		this.type = _type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void update(float deltaTime) {

		if (this.getStageFlag() == OBJ_D_LEFT) {
			velocity.set(-ENEMY_SWORDMAN_VELOCITY, 0);
			this.fCurrentSwingTime = 0f;
		} else if (this.getStageFlag() == OBJ_D_STOP) {
			velocity.set(0, 0);
			this.fCurrentSwingTime = 0f;
		} else if (this.getStageFlag() == OBJ_D_ATTAK) {
			velocity.set(0, 0);
			this.fCurrentSwingTime += deltaTime;
		} else if (this.getStageFlag() == OBJ_D_DEATH) {
			velocity.set(0, 0);
			fCurrentSwingTime = 0;
		}

		if (this.fCurrentSwingTime > nDelaySwingTime) {
			if (SWINGPERSENTAGE > rnd.nextInt(10)/* Skill Effect */) {
				Attack();
				this.fCurrentSwingTime = 0;
			}
		}

		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		stateTime += deltaTime;

		attackBounds.lowerLeft.set(position).sub(attackBounds.width / 2,
				attackBounds.height / 2);
		bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);

		this.getEnemyHpBar().update(position.x, position.y, GAPHPBAR,
				this.getnHealthPoint(), this.getnFullHealThPoint(), deltaTime);
	}

	private void Attack() {
		Manage_Assets.playSound(Manage_Assets.soundSwordAttack01thick);
		if (this.getType() == TYPE01)
			MainGame_Manager.enemyAttackFiyld.add(new AttackFiyld(
					this.position.x - 55, this.position.y,
					AttackFiyld.MIDDLEBOSSATTACK01));
		else if (this.getType() == TYPE02)
			MainGame_Manager.enemyAttackFiyld.add(new AttackFiyld(
					this.position.x - 55, this.position.y,
					AttackFiyld.MIDDLEBOSSATTACK02));
	}

	@Override
	int skill01() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int skill02() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int skill03() {
		// TODO Auto-generated method stub
		return 0;
	}

}
