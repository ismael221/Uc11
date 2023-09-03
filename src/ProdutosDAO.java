/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
       conn = new conectaDAO().connectDB();
       String sql = "INSERT INTO produtos(nome,valor,status) VALUES (?,?,?)";
        try{
            PreparedStatement  stmt = this.conn.prepareStatement(sql);
            stmt.setString(1,produto.getNome());
            stmt.setString(2,produto.getValor().toString());
            stmt.setString(3,produto.getStatus());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao inserir produto: "+ e.getMessage());    
        }
       
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos";
       
        try{
        PreparedStatement  stmt = this.conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
           while(rs.next()){
            ProdutosDTO produtos = new ProdutosDTO();
            produtos.setId(rs.getInt("id"));
            produtos.setNome(rs.getString("nome"));
            produtos.setValor(Integer.parseInt(rs.getString("valor")));
            produtos.setStatus(rs.getString("status"));
            listagem.add(produtos);
           }
        
        }catch(Exception e){
            System.out.println("Erro ao carregar produtos: "+e.getMessage());
        }
        return listagem;
    }
    
    
    
        
}

