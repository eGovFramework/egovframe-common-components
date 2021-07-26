package egovframework.com.sec.captcha.service.impl;

import egovframework.com.sec.captcha.service.EgovCaptchaService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import javax.imageio.ImageIO;
import reactor.util.StringUtils;

public abstract class EgovAbstractCaptchaService extends EgovAbstractServiceImpl implements
    EgovCaptchaService {

  private static final String CAPTCHA_IMAGE_FORMAT = "jpeg";

  private static final SecureRandom random = new SecureRandom();

  private static final String[] fontFamily = {"sans-serif", "arial", "courier", "verdana"};

  private static final Color[] obstacleColors =
      {Color.WHITE, Color.BLACK, Color.GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY};


  private int characterLength = 6;

  private int width;

  private int height;

  private int difficulty;


  protected EgovAbstractCaptchaService() {
    this(150, 50, 30);
  }

  protected EgovAbstractCaptchaService(int width, int height, int difficulty) {
    this.width = width;
    this.height = height;
    this.difficulty = difficulty;
  }


  public void setCharacterLength(int characterLength) {
    this.characterLength = characterLength;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }


  @Override
  public String generateCaptcha() {
    final int limit = (int) Math.pow(10, characterLength);
    final int value = random.nextInt(limit);
    final String format = "%0" + characterLength + "d";

    return String.format(format, value);
  }

  @Override
  public void writeCaptcha(OutputStream out) throws IOException {
    ImageIO.write(generate(generateCaptcha()), CAPTCHA_IMAGE_FORMAT, out);
  }

  private BufferedImage generate(String randomKey) {
    final BufferedImage captcha = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    final Font font =
        new Font(fontFamily[number(fontFamily.length)], Font.BOLD, height - (height >> 2));

    if (StringUtils.isEmpty(randomKey)) {
      throw new IllegalArgumentException();
    }

    final Graphics2D g = (Graphics2D) captcha.getGraphics();
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    background(g);
    drawString(g, font, randomKey);
    obstacle(g);

    return captcha;
  }

  private void drawString(Graphics2D g, Font font, String randomKey) {
    int whole = font.getSize();
    int half = whole >> 1;
    int gap = (height - whole) / 2;
    int x = width / characterLength;
    int i = 0;

    g.setFont(font);
    g.setColor(Color.BLACK);

    for (char c : randomKey.toCharArray()) {
      int xi = (x * i++);
      drawCharacter(
          Character.toString(c),
          g.create(xi - half, 0, whole + half, height),
          whole, half, gap
      );
    }
  }

  private void drawCharacter(String character, Graphics g, int whole, int half, int gap) {
    int rotate = rangedNumber(-45, 45);
    int rotateSuspend = rotate / 10 * 3;

    ((Graphics2D) g).rotate(rotate / 100D);
    g.drawString(character,
        half + rotateSuspend,
        rangedNumber(whole - gap, whole + gap)
    );
  }

  private void background(Graphics2D g) {
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);
  }

  private void obstacle(Graphics2D g) {
    for (int i = 0; i < difficulty; i++) {
      g.setColor(obstacleColors[number(obstacleColors.length)]);
      g.setStroke(new BasicStroke(rangedNumber(1, 2)));
      g.drawLine(number(width), number(height), number(width), number(height));
    }
  }

  private int rangedNumber(int i, int j) {
    return i + number(j - i);
  }

  private int number(int i) {
    return random.nextInt(i);
  }

}
