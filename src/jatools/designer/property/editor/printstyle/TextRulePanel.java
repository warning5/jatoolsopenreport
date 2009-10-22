package jatools.designer.property.editor.printstyle;

import jatools.engine.css.CSSValue;
import jatools.engine.css.rule.TextRule;
import jatools.swingx.SwingUtil;
import jatools.swingx.TemplateComboBox;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.apache.commons.lang.ArrayUtils;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.5 $
  */
public class TextRulePanel extends JPanel {
    private static String[] vNumber = {
            "",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
        };
    private static final String[] autoSizePrompts = {
            "",
            "自动宽度",
            "自动高度",
            "打不下时,自动分页"
        };
    private static final String[] autoSizeValues = {
            "",
            "width",
            "height",
            "breakable"
        };
    private String[] lineEditablePrompts = {
            "",
            "可以",
            "不可以"
        };
    TemplateComboBox levelCombo;
    TemplateComboBox autoWidthCombo = new TemplateComboBox(autoSizePrompts);
    TemplateComboBox maxWidthCombo = new TemplateComboBox(new String[] {
                ""
            });
    

    /**
     * Creates a new TextRulePanel object.
     */
    public TextRulePanel() {
        // TODO Auto-generated constructor stub
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        //        gbc.insets = new Insets(10, 0, 0, 0);
        add(new JLabel("向下合并级别:", JLabel.LEFT), gbc);
        //gbc.fill=gbc.HORIZONTAL;
        gbc.gridwidth = gbc.REMAINDER;
        gbc.gridheight = 1;
        gbc.weightx = 100;
        levelCombo = new TemplateComboBox(vNumber);
        add(levelCombo, gbc);
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(new JLabel("自动大小:", JLabel.LEFT), gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(autoWidthCombo, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(new JLabel("最大宽度:", JLabel.LEFT), gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(maxWidthCombo, gbc);

     

        gbc.weightx = 0;
        gbc.weighty = 100;
        add(Box.createVerticalGlue(), gbc);
        SwingUtil.setBorder6(this);
    }

    void setRule(TextRule rule) {
        clear();

        if ((rule != null) && (!rule.isNull())) {
            if (rule.autoSize != null) {
                int i = ArrayUtils.indexOf(autoSizeValues, rule.autoSize.toString());

                if (i != -1) {
                    this.autoWidthCombo.setText(autoSizePrompts[i]);
                } else {
                    this.autoWidthCombo.setText(rule.autoSize.toString());
                }
            }

            if (rule.unitedLevel != null) {
                this.levelCombo.setText(rule.unitedLevel.toString());
            }

            if (rule.maxWidth != null) {
                this.maxWidthCombo.setText(rule.maxWidth.toString());
            }

           
        }
    }

    void clear() {
        this.levelCombo.setText(null);
        this.autoWidthCombo.setText(null);
        this.maxWidthCombo.setText(null);
        
    }

    TextRule getRule() {
        TextRule rule = new TextRule();

        if ((this.levelCombo.getText() != null) && (this.levelCombo.getText().trim().length() > 0)) {
            String level = this.levelCombo.getText();

            rule.unitedLevel = new CSSValue(level);
        }

        String autoWidth = this.autoWidthCombo.getText();

        if ((autoWidth != null) && (autoWidth.trim().length() > 0)) {
            autoWidth = autoWidth.trim();

            int i = ArrayUtils.indexOf(autoSizePrompts, autoWidth);

            if (i != -1) {
                autoWidth = autoSizeValues[i];
            }

            rule.autoSize = new CSSValue(autoWidth);
        }

        if ((this.maxWidthCombo.getText() != null) &&
                (this.maxWidthCombo.getText().trim().length() > 0)) {
            String maxWidth = this.maxWidthCombo.getText();

            rule.maxWidth = new CSSValue(maxWidth);
        }

       

        if (!rule.isNull()) {
            return rule;
        } else {
            return null;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        JFrame frame = new JFrame("文本");
        frame.setResizable(true);

        TextRulePanel p = new TextRulePanel();
        frame.setSize(new Dimension(350, 250)); //new Dimension(200,100)

        Container contentPane = frame.getContentPane();

        contentPane.add(p, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
