package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.TextArea;
import javax.swing.DropMode;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;

public class Ventana extends JFrame implements IVista, KeyListener, MouseListener {

	private JPanel contentPane;
	private JTextField RtaDNI;
	private JTextField RtaComercioVivienda;
	private JTextField RtaCamaras;
	private JTextField RtaBotones;
	private JTextField RtaMovil;
	private JTextField textFieldNombreTecnico;
	private JButton botonPagar;
	private JButton botonContratarServicio;
	private JButton botonDarDeBaja;
	private JButton botonHistorico;
	private JButton botonGestionFact;
	private JButton botonActualizarMes;
	private JButton botonSolicitarTecnico;
	private JButton botonDarDeAltaTecnico;
	private ActionListener actionListener;
	private JTextArea textArea_LOG;
	

	
	 
	public static void main(String[] args) { // DPS BORRAR ESTE MAIN
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 */
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(4, 3, 0, 0));
		
		JPanel panelDNI = new JPanel();
		panelCentral.add(panelDNI);
		panelDNI.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel LabelDNI = new JLabel("DNI:");
		LabelDNI.setHorizontalAlignment(SwingConstants.CENTER);
		panelDNI.add(LabelDNI);
		
		JPanel panelDNItextfield = new JPanel();
		panelDNI.add(panelDNItextfield);
		panelDNItextfield.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panelDNItextfield.add(panel);
		
		this.RtaDNI = new JTextField();
		this.RtaDNI.addKeyListener(this);
		panelDNItextfield.add(RtaDNI);
		RtaDNI.setColumns(10);
		
		JPanel panelDatosContratacion = new JPanel();
		panelCentral.add(panelDatosContratacion);
		panelDatosContratacion.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelLabelsContratacion = new JPanel();
		panelDatosContratacion.add(panelLabelsContratacion);
		panelLabelsContratacion.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel TipoContratacion = new JLabel("Comercio/vivienda:");
		TipoContratacion.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabelsContratacion.add(TipoContratacion);
		
		JLabel CantCamaras = new JLabel("Cantidad de cámaras:");
		CantCamaras.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabelsContratacion.add(CantCamaras);
		
		JLabel BotonesAntipanico = new JLabel("Cantidad de botones:");
		BotonesAntipanico.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabelsContratacion.add(BotonesAntipanico);
		
		JLabel MovilAcomp = new JLabel("Movil acompañaniento?");
		MovilAcomp.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabelsContratacion.add(MovilAcomp);
		
		JPanel panelRtaContratacion = new JPanel();
		panelDatosContratacion.add(panelRtaContratacion);
		panelRtaContratacion.setLayout(new GridLayout(4, 1, 0, 0));
		
		this.RtaComercioVivienda = new JTextField();
		this.RtaComercioVivienda.addKeyListener(this);
		panelRtaContratacion.add(RtaComercioVivienda);
		RtaComercioVivienda.setColumns(10);
		
		this.RtaCamaras = new JTextField();
		this.RtaCamaras.addKeyListener(this);
		panelRtaContratacion.add(RtaCamaras);
		RtaCamaras.setColumns(10);
		
		this.RtaBotones = new JTextField();
		this.RtaBotones.addKeyListener(this);
		panelRtaContratacion.add(RtaBotones);
		RtaBotones.setColumns(10);
		
		this.RtaMovil = new JTextField();
		this.RtaMovil.addKeyListener(this);
		panelRtaContratacion.add(RtaMovil);
		RtaMovil.setColumns(10);
		
		JPanel panelBotonesPagar_Contratar_Baja = new JPanel();
		panelCentral.add(panelBotonesPagar_Contratar_Baja);
		panelBotonesPagar_Contratar_Baja.setLayout(new GridLayout(2, 3, 0, 0));
		
		botonPagar = new JButton("Pagar factura");
		botonPagar.setEnabled(false);
		botonPagar.setActionCommand("PAGAR");
		panelBotonesPagar_Contratar_Baja.add(botonPagar);
		
		botonContratarServicio = new JButton("Contratar Servicio");
		botonContratarServicio.setActionCommand("CONTRATAR");
		botonContratarServicio.setEnabled(false);
		panelBotonesPagar_Contratar_Baja.add(botonContratarServicio);
		
		botonDarDeBaja = new JButton("Dar de baja servicio");
		botonDarDeBaja.setActionCommand("DARBAJA");
		botonDarDeBaja.setEnabled(false);
		panelBotonesPagar_Contratar_Baja.add(botonDarDeBaja);
		
		botonHistorico = new JButton("Solicitar histórico");
		botonHistorico.setActionCommand("HISTORICO");
		botonHistorico.setEnabled(false);
		panelBotonesPagar_Contratar_Baja.add(botonHistorico);
		
		botonGestionFact = new JButton("Gestionar Facturacion");
		botonGestionFact.setActionCommand("FACTURACION");
		panelBotonesPagar_Contratar_Baja.add(botonGestionFact);
		
		botonActualizarMes = new JButton("Actualizar mes");
		botonActualizarMes.setActionCommand("ACTUALIZARMES");
		panelBotonesPagar_Contratar_Baja.add(botonActualizarMes);
		
		JPanel PanelTecnico = new JPanel();
		panelCentral.add(PanelTecnico);
		PanelTecnico.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel PanelSolicitarTecnico = new JPanel();
		PanelTecnico.add(PanelSolicitarTecnico);
		PanelSolicitarTecnico.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel PanelDiseñoTecnico = new JPanel();
		PanelSolicitarTecnico.add(PanelDiseñoTecnico);
		
		botonSolicitarTecnico = new JButton("Solicitar técnico");
		botonSolicitarTecnico.setActionCommand("SOLICITARTECNICO");
		botonSolicitarTecnico.setEnabled(false);
		PanelSolicitarTecnico.add(botonSolicitarTecnico);
		
		JPanel PanelDarDeAltaTecnico = new JPanel();
		PanelTecnico.add(PanelDarDeAltaTecnico);
		PanelDarDeAltaTecnico.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_aux_darDeAltaTecnico = new JPanel();
		PanelDarDeAltaTecnico.add(panel_aux_darDeAltaTecnico);
		
		JPanel panel_2 = new JPanel();
		PanelDarDeAltaTecnico.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel LabelNombreTecnico = new JLabel("Nombre:");
		LabelNombreTecnico.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(LabelNombreTecnico);
		
		this.textFieldNombreTecnico = new JTextField();
		this.textFieldNombreTecnico.addKeyListener(this);
		panel_2.add(textFieldNombreTecnico);
		textFieldNombreTecnico.setColumns(10);
		
		botonDarDeAltaTecnico = new JButton("Dar de alta tecnico");
		botonDarDeAltaTecnico.setActionCommand("ALTATECNICO");
		botonDarDeAltaTecnico.setEnabled(false);
		PanelDarDeAltaTecnico.add(botonDarDeAltaTecnico);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		this.textArea_LOG = new JTextArea();
		this.textArea_LOG.setBounds(0, 0, 1, 1);
		this.textArea_LOG.setEditable(false);
		panelSur.add(textArea_LOG);
		
		this.muestraMensaje("---------- Aqui se mostrarán los mensajes del sistema -----------");
		
		// this.setVisible(true);
	}

	public void keyPressed(KeyEvent e) {
	}
	
	public void keyReleased(KeyEvent e) { // validacion de datos - FUNCIONA OK
		
		String dni="";
		String tipoServicio="";
		String movil="";
		String nombreTecnico;
		int cantBotones=-1,cantCamaras=-1;
		boolean condicion, condicionDNI, condicionTecnico;
		
		try{
			cantBotones = Integer.parseInt(this.RtaBotones.getText());
			cantCamaras = Integer.parseInt(this.RtaCamaras.getText());
		}
		catch (NumberFormatException exec) {
		}
		
		dni = this.RtaDNI.getText();
		tipoServicio = this.RtaComercioVivienda.getText();
		movil = this.RtaMovil.getText();
		nombreTecnico = this.textFieldNombreTecnico.getText();
		
		condicionDNI = !dni.equals("");
		condicion = condicionDNI && cantBotones>=0 && cantCamaras>=0 && (tipoServicio.equalsIgnoreCase("vivienda")||tipoServicio.equalsIgnoreCase("comercio")) && (movil.equalsIgnoreCase("si")||movil.equalsIgnoreCase("no"));
		condicionTecnico = !(nombreTecnico.isBlank());
		
		this.botonPagar.setEnabled(condicionDNI); 
		this.botonContratarServicio.setEnabled(condicion);
		this.botonDarDeBaja.setEnabled(condicionDNI);
		this.botonHistorico.setEnabled(condicionDNI); 
		this.botonPagar.setEnabled(condicionDNI); 
		this.botonDarDeAltaTecnico.setEnabled(condicionTecnico);
		this.botonSolicitarTecnico.setEnabled(condicionDNI);

	}
	
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		
		this.botonActualizarMes.addActionListener(actionListener); 
		this.botonContratarServicio.addActionListener(actionListener); 
		this.botonDarDeAltaTecnico.addActionListener(actionListener); 
		this.botonDarDeBaja.addActionListener(actionListener); 
		this.botonGestionFact.addActionListener(actionListener); 
		this.botonHistorico.addActionListener(actionListener); 
		this.botonPagar.addActionListener(actionListener); 
		this.botonSolicitarTecnico.addActionListener(actionListener); 
		this.actionListener=actionListener;
		
	}
	
	public void muestraMensaje (String mensaje) {
		textArea_LOG.setText(mensaje);
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) 
	{
		ActionEvent event;
		JButton botonApretado = (JButton)e.getSource();
		String command = botonApretado.getActionCommand(); // lo que se debe hacer
		event = new ActionEvent(botonApretado,0,command);
		if (e.getButton() == 1) //boton izq
			this.actionListener.actionPerformed(event);
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
