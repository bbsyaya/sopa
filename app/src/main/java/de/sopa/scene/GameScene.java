package de.sopa.scene;


import android.view.GestureDetector;
import de.sopa.MainActivity;
import de.sopa.SwipeGestureDetector;
import de.sopa.Tile;
import de.sopa.TouchListener;
import de.sopa.manager.ResourcesManager;
import de.sopa.manager.SceneManager;
import de.sopa.model.GameService;
import de.sopa.model.GameServiceImpl;
import de.sopa.observer.Observer;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TextureRegion;

/**
 * David Schilling - davejs92@gmail.com
 */
public class GameScene extends BaseScene implements Observer {

    private GameService gameService;
    private GestureDetector gestureDetector;
    private Sprite solvedSprite;


    public void addTiles() {
        this.clearChildScene();
        Tile[][] field = gameService.getGameField().getField();
        int width = field.length;
        int heigth = field[0].length;
        int spacePerTile = MainActivity.CAMERA_WIDTH / width;
        int tilePositionY = 0;
        for (int y = 0; y < heigth; y++) {
            int tilePositionX = 0;
            for (int x = 0; x < width; x++) {
                if (field[x][y].getShortcut() != 'n') {
                    TextureRegion pTextureRegion = ResourcesManager.getInstance().regionTileMap.get(field[x][y].getShortcut());
                    Sprite tileSprite = new Sprite(tilePositionX, tilePositionY, spacePerTile, spacePerTile, pTextureRegion, vbom);
                    this.attachChild(tileSprite);
                }
                tilePositionX += spacePerTile;
            }
            tilePositionY += spacePerTile;
        }
        setSolved(gameService.solvedPuzzle());

    }


    public void setSolved(boolean solved) {
        if (solved) {
            solvedSprite = new Sprite(0, 0, 50, 50, ResourcesManager.getInstance().regionTileMap.get('s'), vbom);
        } else {
            solvedSprite = new Sprite(0, 0, 50, 50, ResourcesManager.getInstance().regionTileMap.get('i'), vbom);
        }
        attachChild(solvedSprite);
    }

    @Override
    public void update() {
        addTiles();
    }

    @Override
    public void setSubject(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void createScene() {
        initializeLogic();
        addTiles();
    }

    private void initializeLogic() {
        gameService = new GameServiceImpl();
        gameService.startGame();
        gameService.attach(this);
        final int widthPerTile = MainActivity.CAMERA_WIDTH / gameService.getGameField().getField().length;
        final Scene scene = this;
        //TODO: this should be done better
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gestureDetector = new GestureDetector(new SwipeGestureDetector(gameService, widthPerTile, widthPerTile, widthPerTile));
                TouchListener touchListener = new TouchListener(gestureDetector);
                scene.setOnSceneTouchListener(touchListener);
            }
        });
    }

    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().loadMenuScene(engine);
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene() {
        //TODO: remove scene and children
    }
}
