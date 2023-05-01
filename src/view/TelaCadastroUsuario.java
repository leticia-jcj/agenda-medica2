/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.UsuarioController;
import Entidades.Perfil;
import Entidades.Usuario;
import java.awt.Component;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ivanoliveira
 */
public class TelaCadastroUsuario extends javax.swing.JFrame {

    UsuarioController usuarioController = new UsuarioController();

    DefaultTableModel tabelaPadrao;

    DefaultTableCellRenderer renderizadorDeCelula = new DefaultTableCellRenderer();

    public TelaCadastroUsuario() throws SQLException {

        initComponents();

        // posicionar tela no centro 
        setLocationRelativeTo(null);

        renderizadorDeCelula.setHorizontalAlignment(SwingConstants.CENTER);

        // centralizando cabeçalho da tabela 
        tabelaUsuariosCadastrados.getTableHeader().setDefaultRenderer(renderizadorDeCelula);

        // centralizar algumas colunas da tabela
        // centralizando o conteúdo da coluna id 
        // tabelaUsuariosCadastrados.getColumnModel().getColumn(0).setCellRenderer(renderizadorDeCelula);
        // tabelaUsuariosCadastrados.getColumnModel().getColumn(1).setCellRenderer(renderizadorDeCelula);
        for (int i = 0; i < tabelaUsuariosCadastrados.getColumnCount(); i++) {

            if (i != 2) {

                tabelaUsuariosCadastrados.getColumnModel().getColumn(i).setCellRenderer(renderizadorDeCelula);
            }
        }

        for (Perfil perfil : usuarioController.listarTodosPerfis()) {

            System.out.println(perfil);

            comboBoxPerfil.addItem(perfil);

        }

        tabelaPadrao = (DefaultTableModel) tabelaUsuariosCadastrados.getModel();

        atualizarTabela();

    }

    private void atualizarTabela() throws SQLException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Usuario usuario : usuarioController.listarTodosuUsuarios()) {

            tabelaPadrao.addRow(
                    new Object[]{
                        usuario.getId(),
                        usuario.getCpf(),
                        usuario.getNome(),
                        usuario.getUsername(),
                        usuario.getSenha(),
                        usuario.getTelefone(),
                        usuario.getMatricula(),
                        usuario.getPerfil(),
                        sdf.format(usuario.getDataNascimento())

                    }
            );

        }

    }

    private void limpaFormulario() {

        for (int i = 0; i < painelDeDados.getComponentCount(); i++) {

            Component componente = painelDeDados.getComponent(i);

            if (componente instanceof JTextField) {

                JTextField campo = (JTextField) componente;
                campo.setText("");

            }

            if (componente instanceof JFormattedTextField) {

                JFormattedTextField campo1 = (JFormattedTextField) componente;
                campo1.setText("");

            }

        }

    }

    private void refreshTabela() throws SQLException {
        tabelaPadrao.setNumRows(0);

        atualizarTabela();

        tabelaPadrao.fireTableDataChanged();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelDeDados = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        txtDataNascimento = new javax.swing.JFormattedTextField();
        botaoCadastrar = new javax.swing.JButton();
        txtCPFformatado = new javax.swing.JFormattedTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        comboBoxPerfil = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaUsuariosCadastrados = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastrar Usuário");
        setResizable(false);

        painelDeDados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formulário de cadastro de usuário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("CPF:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("NOME");

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("USERNAME");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("SENHA");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("TELEFONE");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("DATA DE NASCIMENTO");

        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtSenha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        try {
            txtDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataNascimento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        botaoCadastrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        botaoCadastrar.setText("CADASTRAR");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        try {
            txtCPFformatado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCPFformatado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        comboBoxPerfil.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("PERFIL");

        javax.swing.GroupLayout painelDeDadosLayout = new javax.swing.GroupLayout(painelDeDados);
        painelDeDados.setLayout(painelDeDadosLayout);
        painelDeDadosLayout.setHorizontalGroup(
            painelDeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDeDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDeDadosLayout.createSequentialGroup()
                        .addGroup(painelDeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDeDadosLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtCPFformatado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelDeDadosLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelDeDadosLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(32, 32, 32)
                                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(painelDeDadosLayout.createSequentialGroup()
                        .addGroup(painelDeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDeDadosLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelDeDadosLayout.createSequentialGroup()
                                .addGap(362, 362, 362)
                                .addComponent(botaoCadastrar))
                            .addGroup(painelDeDadosLayout.createSequentialGroup()
                                .addGap(361, 361, 361)
                                .addGroup(painelDeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(painelDeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(painelDeDadosLayout.createSequentialGroup()
                                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 265, Short.MAX_VALUE))))
        );
        painelDeDadosLayout.setVerticalGroup(
            painelDeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDeDadosLayout.createSequentialGroup()
                .addGroup(painelDeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDeDadosLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDeDadosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtCPFformatado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(painelDeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelDeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelDeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(painelDeDadosLayout.createSequentialGroup()
                        .addGroup(painelDeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(1, 1, 1)))
                .addGap(23, 23, 23)
                .addComponent(botaoCadastrar)
                .addGap(26, 26, 26))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/banner.PNG"))); // NOI18N

        tabelaUsuariosCadastrados.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tabelaUsuariosCadastrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CPF", "NOME", "USERNAME", "SENHA", "TELEFONE", "MATRÍCULA", "PERFIL", "DATA DE NASCIMENTO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaUsuariosCadastrados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tabelaUsuariosCadastrados);
        if (tabelaUsuariosCadastrados.getColumnModel().getColumnCount() > 0) {
            tabelaUsuariosCadastrados.getColumnModel().getColumn(0).setResizable(false);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(0).setPreferredWidth(70);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(1).setResizable(false);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(1).setPreferredWidth(120);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(2).setResizable(false);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(2).setPreferredWidth(250);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(3).setResizable(false);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(3).setPreferredWidth(120);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(4).setResizable(false);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(5).setResizable(false);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(5).setPreferredWidth(150);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(6).setResizable(false);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(6).setPreferredWidth(130);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(7).setResizable(false);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(7).setPreferredWidth(170);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(8).setResizable(false);
            tabelaUsuariosCadastrados.getColumnModel().getColumn(8).setPreferredWidth(170);
        }

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton2.setText("EXCLUIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton3.setText("ALTERAR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(painelDeDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1038, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(28, 28, 28)
                .addComponent(painelDeDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed

        Usuario usuario = new Usuario();

        usuario.setCpf(txtCPFformatado.getText().replace("-", "").replace(".", "").trim());

        usuario.setNome(txtNome.getText().trim());

        usuario.setUsername(txtUsername.getText().trim().toLowerCase());

        usuario.setSenha(String.valueOf(txtSenha.getPassword()).trim());

        usuario.setTelefone(txtTelefone.getText());

        usuario.setPerfil((Perfil) comboBoxPerfil.getSelectedItem());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {

            usuario.setDataNascimento(sdf.parse(txtDataNascimento.getText()));

            usuarioController.cadastrarUsuario(usuario);

            limpaFormulario();

            refreshTabela();

        } catch (ParseException ex) {

            JOptionPane.showMessageDialog(null, "A data de nascimento deve ser preenchida!");

        } catch (SQLException ex) {

            System.out.println(ex);

        }


    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int linhaSelecionada = -1;

        linhaSelecionada = tabelaUsuariosCadastrados.getSelectedRow();

        if (linhaSelecionada >= 0) {
            
            
       int  idUsuario = (Integer) tabelaUsuariosCadastrados.getValueAt(linhaSelecionada, 0);
       
            try {
                usuarioController.excluir(idUsuario);
                
                refreshTabela();
                
                
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }

            
       
       
       

        } else {

            JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha");

        }


    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaCadastroUsuario().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JComboBox<Perfil> comboBoxPerfil;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel painelDeDados;
    private javax.swing.JTable tabelaUsuariosCadastrados;
    private javax.swing.JFormattedTextField txtCPFformatado;
    private javax.swing.JFormattedTextField txtDataNascimento;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JFormattedTextField txtTelefone;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
