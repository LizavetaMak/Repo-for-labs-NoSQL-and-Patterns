package com.musicalinstrument.demo.TTPP.render;

import com.musicalinstrument.demo.TTPP.Dao_for_brands.*;
import com.musicalinstrument.demo.TTPP.common.IEventListener;
import com.musicalinstrument.demo.TTPP.memento.BrandMemento;
import com.musicalinstrument.demo.TTPP.memento.BrandsHistory;
import com.musicalinstrument.demo.jpa.Brand;
import com.musicalinstrument.demo.jpa.Role;
import com.musicalinstrument.demo.jpa.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimpleApplication extends JPanel {
    JPanel brandsPanel;
    JPanel loginPanel;
    JPanel registerPanel;
    JPanel addBrandPanel;
    JTabbedPane tabbedPane = null;
    IDAO mysql;
    JFrame frame;
    BrandsHistory history = new BrandsHistory();
    User currentUser = null;
    public static String BRAND_CHANGE = "BRAND_CHANGE";
    public static String NEW_BRAND = "NEW_BRAND";
    public static String DELETE_BRAND = "DELETE_BRAND";
    public SimpleApplication (JFrame frame) throws Exception {
        super (new GridLayout(1, 0));
        this.frame = frame;

        DAOFactory factory = new DAOFactory();
        mysql = new DAO_Proxy(factory.createDao(EDAOType.MySQL));

        Border paneEdge = BorderFactory.createEmptyBorder(0, 10, 10, 10);

        
        brandsPanel = new JPanel();
        brandsPanel.setBorder(paneEdge);
        brandsPanel.setLayout(new BoxLayout(brandsPanel, BoxLayout.Y_AXIS));

        IEventListener renderBrands = (IDAO mysql) -> {
            try {
                brandsPanel.removeAll();
                renderAllBrands();
            } catch (Exception error) {
                System.out.println(error);
            }

        };

        mysql.subscribe(NEW_BRAND, renderBrands);
        mysql.subscribe(DELETE_BRAND, renderBrands);
        mysql.subscribe(BRAND_CHANGE, renderBrands);

        renderAllBrands();

        addBrandPanel = new JPanel();
        renderAddBrandPanel();
        renderLoginPanel();
        renderRegisterPanel();

        renderTabs();
    }

    private void renderAllBrands () throws Exception {
        for (Brand brand: mysql.getAllBrands(ERole.ADMIN))
            renderBrandComponent(brandsPanel, brand);
    }


    private void renderBrandComponent(JPanel target, Brand brand) {
        JPanel brandComponent = new JPanel(new GridLayout(1, 5), false);
        JLabel brandName = new JLabel(brand.getName(), JLabel.CENTER);
        //JLabel brandCountry = new JLabel(brand.getCountry(), JLabel.CENTER);
        JButton updateButton = new JButton("Upd");
        JButton deleteButton = new JButton("Del");
        JButton revertButton = new JButton("Revert");

        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                try {
                    mysql.deleteBrand(brand, getUserRole());
                    history.clearHistoryForBrand(brand.getId());
                }  catch (NotEnoughPermissionsException exception)
                {
                    JOptionPane.showMessageDialog(loginPanel, "У вас недостаточно привилегий для этого действия.", "", JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String newBrandName =  (String) JOptionPane.showInputDialog(
                        frame,
                        "Enter new brand name",
                        "Edit Brand",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        brand.getName()
                );

                if (newBrandName != null && newBrandName.length() > 0) {
                    try {
                        history.rememberState(brand.getId(), new BrandMemento(brand));
                        brand.setName(newBrandName);
                        mysql.updateBrand(brand,getUserRole());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        revertButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    Brand restoredBrand = history.revert(brand.getId());
                    if (restoredBrand != null)
                        mysql.updateBrand(restoredBrand,getUserRole());
                }
                catch (NotEnoughPermissionsException exception)
                {
                    JOptionPane.showMessageDialog(loginPanel, "У вас недостаточно привилегий для этого действия.", "", JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        brandComponent.add(brandName);
        brandComponent.add(revertButton);
        brandComponent.add(deleteButton);
        brandComponent.add(updateButton);
        brandComponent.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        target.add(Box.createRigidArea(new Dimension(0, 10)));
        target.add(brandComponent);
    }

    private void renderAddBrandPanel() {
        addBrandPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        addBrandPanel.setLayout(new BoxLayout(addBrandPanel, BoxLayout.Y_AXIS));

        TextField brandNameInput = new TextField();
        brandNameInput.setText("Enter brand name!");

        Button create = new Button("Create");

        create.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    mysql.createBrand(new Brand(brandNameInput.getText(),"county", (long)-1), getUserRole());
                    brandNameInput.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        addBrandPanel.add(brandNameInput);
        addBrandPanel.add(create);


    }
    private void renderLoginPanel () {
        loginPanel = new JPanel();
        loginPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        TextField login = new TextField();
        login.setText("Enter login");

        TextField password = new TextField();
        password.setText("Enter password");

        Button create = new Button("Login");

        create.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                try {
                    currentUser = mysql.login(login.getText(), password.getText());

                    renderTabs();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(loginPanel, "Login error. Пользователь или не существует или неправильный login and password", "", JOptionPane.ERROR_MESSAGE);

                    exception.printStackTrace();
                }
            }
        });

        loginPanel.add(login);
        loginPanel.add(password);
        loginPanel.add(create);
    }

    private void renderRegisterPanel() {
        registerPanel = new JPanel();
        registerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));

        TextField login = new TextField();
        login.setText("Enter login");

        TextField password = new TextField();
        password.setText("Enter password");

        JComboBox<String> selectRole = new JComboBox<String>(new String[]{"ADMIN", "USER"});

        Button create = new Button("Register");

        create.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                try {
                    Role role = new Role();
                    role.setRoleId(selectRole.getSelectedIndex() + 1);

                    currentUser = mysql.register(login.getText(), password.getText(), role);

                    renderTabs();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(loginPanel, "Register error. Пользователь или существует или неправильный login and password", "", JOptionPane.ERROR_MESSAGE);

                    exception.printStackTrace();
                }
            }
        });

        registerPanel.add(login);
        registerPanel.add(password);
        registerPanel.add(selectRole);
        registerPanel.add(create);
    }

    private ERole getUserRole () {
        return currentUser.getFkRoleId() == 1 ? ERole.ADMIN : ERole.USER;
    }

    private void renderTabs () {
        if (tabbedPane != null)
            remove(tabbedPane);

        tabbedPane = new JTabbedPane();

        if (isLogined())
        {
            tabbedPane.addTab("Brands", null, brandsPanel, "Use this panel to observe brands");
            tabbedPane.addTab("Add Brand", null, addBrandPanel, "Use this panel to add brand");
        }
        else
        {
            tabbedPane.addTab("Login", null, loginPanel, "Login here");
            tabbedPane.addTab("Register", null, registerPanel, "Register here");
        }


        tabbedPane.setSelectedIndex(0);

        add(tabbedPane);

        revalidate();
    }

    private boolean isLogined () {
        return currentUser != null;
    }


    public static void createAndShowGUI () throws Exception {
        JFrame frame = new JFrame("Simple Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SimpleApplication app = new SimpleApplication(frame);
        app.setOpaque(true);

        frame.setContentPane(app);

        frame.setSize(200, 200);
        frame.pack();
        frame.setVisible(true);
    }

}
