package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.ActionEventExtended;

import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.KeyEvent;

public class Ventana extends JFrame implements IVista, KeyListener, MouseListener, WindowListener {

	private JPanel contentPane;
	private JTextField RtaDNI;
	private JTextField RtaComercioVivienda;
	private JTextField RtaCamaras;
	private JTextField RtaBotones;
	private JTextField RtaMovil;
	private JTextField RtaNombreTecnico;
	private JButton botonPagar;
	private JButton botonContratarServicio;
	private JButton botonDarDeBaja;
	private JButton botonHistorico;
	private JButton botonGestionFact;
	private JButton botonActualizarMes;
	private JButton botonSolicitarTecnico;
	private JButton botonDarDeAltaTecnico;
	private ActionListener actionListener;
	private JTextField RtaCalle;
	private JTextField textFieldNumero;
	private JTextField RtaMetodoPago;
	private JTextArea textArea_LOG;

	/**
	 * Create the frame.
	 */
	public Ventana() {
		
		ActionEvent event = new ActionEvent(this,0,"DESPERSISTIR");
		//this.actionListener.actionPerformed(event);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(4, 3, 0, 0));
		
		JPanel panelDNI = new JPanel();
		panelCentral.add(panelDNI);
		panelDNI.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panelDNI.add(panel);
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel LabelDNI = new JLabel("DNI:");
		panel.add(LabelDNI);
		LabelDNI.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel LabelCalle = new JLabel("Calle:");
		LabelCalle.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(LabelCalle);
		
		JLabel LabelNumero = new JLabel("Numero:");
		LabelNumero.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(LabelNumero);
		
		JLabel TipoContratacion = new JLabel("Comercio/vivienda:");
		panel.add(TipoContratacion);
		TipoContratacion.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelDNItextfield = new JPanel();
		panelDNI.add(panelDNItextfield);
		panelDNItextfield.setLayout(new GridLayout(4, 1, 0, 0));
		
		this.RtaDNI = new JTextField();
		this.RtaDNI.addKeyListener(this);
		panelDNItextfield.add(RtaDNI);
		RtaDNI.setColumns(10);
		
		RtaCalle = new JTextField();
		RtaCalle.addKeyListener(this);
		panelDNItextfield.add(RtaCalle);
		RtaCalle.setColumns(10);
		
		textFieldNumero = new JTextField();
		textFieldNumero.addKeyListener(this);
		panelDNItextfield.add(textFieldNumero);
		textFieldNumero.setColumns(10);
		
		this.RtaComercioVivienda = new JTextField();
		panelDNItextfield.add(RtaComercioVivienda);
		this.RtaComercioVivienda.addKeyListener(this);
		RtaComercioVivienda.setColumns(10);
		
		JPanel panelDatosContratacion = new JPanel();
		panelCentral.add(panelDatosContratacion);
		panelDatosContratacion.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelLabelsContratacion = new JPanel();
		panelDatosContratacion.add(panelLabelsContratacion);
		panelLabelsContratacion.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel CantCamaras = new JLabel("Cantidad de cámaras:");
		CantCamaras.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabelsContratacion.add(CantCamaras);
		
		JLabel BotonesAntipanico = new JLabel("Cantidad de botones:");
		BotonesAntipanico.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabelsContratacion.add(BotonesAntipanico);
		
		JLabel MovilAcomp = new JLabel("Movil acompañaniento? (si/no)");
		MovilAcomp.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabelsContratacion.add(MovilAcomp);
		
		JLabel LabelMetodoPago = new JLabel("Efectivo/cheque/tarjeta");
		LabelMetodoPago.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabelsContratacion.add(LabelMetodoPago);
		
		JPanel panelRtaContratacion = new JPanel();
		panelDatosContratacion.add(panelRtaContratacion);
		panelRtaContratacion.setLayout(new GridLayout(4, 1, 0, 0));
		
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
		
		RtaMetodoPago = new JTextField();
		panelRtaContratacion.add(RtaMetodoPago);
		RtaMetodoPago.addKeyListener(this);
		RtaMetodoPago.setColumns(10);
		
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
		
		JPanel PanelDarDeAltaTecnico = new JPanel();
		PanelTecnico.add(PanelDarDeAltaTecnico);
		PanelDarDeAltaTecnico.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		PanelDarDeAltaTecnico.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel LabelNombreTecnico = new JLabel("Nombre:");
		LabelNombreTecnico.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(LabelNombreTecnico);
		
		this.RtaNombreTecnico = new JTextField();
		this.RtaNombreTecnico.addKeyListener(this);
		panel_2.add(RtaNombreTecnico);
		RtaNombreTecnico.setColumns(10);
		
		botonDarDeAltaTecnico = new JButton("Dar de alta tecnico");
		botonDarDeAltaTecnico.setActionCommand("ALTATECNICO");
		botonDarDeAltaTecnico.setEnabled(false);
		PanelDarDeAltaTecnico.add(botonDarDeAltaTecnico);
		
		botonSolicitarTecnico = new JButton("Solicitar técnico");
		PanelDarDeAltaTecnico.add(botonSolicitarTecnico);
		botonSolicitarTecnico.setActionCommand("SOLICITARTECNICO");
		botonSolicitarTecnico.setEnabled(false);
		
		JScrollPane scrollPanelSur = new JScrollPane();
		contentPane.add(scrollPanelSur, BorderLayout.SOUTH);
		
		this.textArea_LOG = new JTextArea();
		this.textArea_LOG.setRows(10);
		this.textArea_LOG.setEditable(false);
		this.textArea_LOG.setTabSize(25);
		scrollPanelSur.setViewportView(textArea_LOG);
		this.botonActualizarMes.addMouseListener(this);
		this.botonContratarServicio.addMouseListener(this);
		this.botonDarDeAltaTecnico.addMouseListener(this);
		this.botonDarDeBaja.addMouseListener(this);
		this.botonGestionFact.addMouseListener(this);
		this.botonHistorico.addMouseListener(this);
		this.botonPagar.addMouseListener(this);
		this.botonSolicitarTecnico.addMouseListener(this);
		this.setVisible(true);
		this.addWindowListener(this);
		this.muestraMensaje("---------- Aqui se mostrarán los mensajes del sistema -----------");
		
	}

	public void keyPressed(KeyEvent e) {
	}
	
	public void keyReleased(KeyEvent e) { // validacion de datos - FUNCIONA OK
		
		String dni="";
		String tipoServicio="";
		String movil="";
		String calle="";
		String metodoPago="";
		int numero=-1;
		String nombreTecnico;
		int cantBotones=-1,cantCamaras=-1;
		boolean condicion, condicionDNI, condicionTecnico, condicioncalle;
		
		try{
			cantBotones = Integer.parseInt(this.RtaBotones.getText());
			cantCamaras = Integer.parseInt(this.RtaCamaras.getText());
			numero = Integer.parseInt(this.textFieldNumero.getText());
		}
		catch (NumberFormatException exec) {
		}
		
		dni = this.RtaDNI.getText();
		tipoServicio = this.RtaComercioVivienda.getText();
		movil = this.RtaMovil.getText();
		nombreTecnico = this.RtaNombreTecnico.getText();
		calle = this.RtaCalle.getText();
		metodoPago = this.RtaMetodoPago.getText();
		
		condicionDNI = !dni.equals("");
		condicioncalle = !calle.equals("");
		condicion = condicionDNI && cantBotones>=0 && cantCamaras>=0 && numero>=0 && condicioncalle && (tipoServicio.equalsIgnoreCase("vivienda")||tipoServicio.equalsIgnoreCase("comercio")) && (movil.equalsIgnoreCase("si")||movil.equalsIgnoreCase("no")) && (metodoPago.equalsIgnoreCase("Efectivo")||metodoPago.equalsIgnoreCase("Cheque")||metodoPago.equalsIgnoreCase("Tarjeta"));
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

	public void setActionListener(ActionListener actionListener) {
		
		this.botonActualizarMes.addActionListener(actionListener); 
		this.botonContratarServicio.addActionListener(actionListener); 
		this.botonDarDeAltaTecnico.addActionListener(actionListener); 
		this.botonDarDeBaja.addActionListener(actionListener); 
		this.botonGestionFact.addActionListener(actionListener); 
		this.botonHistorico.addActionListener(actionListener); 
		this.botonPagar.addActionListener(actionListener); 
		this.botonSolicitarTecnico.addActionListener(actionListener); 
		this.actionListener = actionListener;
		
	}
	
	public void muestraMensaje (String mensaje) {
		this.textArea_LOG.append(mensaje+"\n");
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) //
	{
		System.out.println("hiceClick");
		ActionEventExtended event;
		String dni = this.RtaDNI.getText();
		String tipoServicio = this.RtaComercioVivienda.getText();
		String movil = this.RtaMovil.getText();
		String nombreTecnico = this.RtaNombreTecnico.getText();
		String calle = this.RtaCalle.getText();
		String metodoPago = this.RtaMetodoPago.getText();
		int cantBotones = Integer.parseInt(this.RtaBotones.getText());
		int cantCamaras = Integer.parseInt(this.RtaCamaras.getText());
		int numero = Integer.parseInt(this.textFieldNumero.getText());
		
		JButton botonApretado = (JButton)e.getSource();
		String command = botonApretado.getActionCommand(); // lo que se debe hacer
		event = new ActionEventExtended(botonApretado,0,command,dni,calle,numero,tipoServicio,cantBotones,cantCamaras,movil,nombreTecnico,metodoPago);
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



	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowClosing(WindowEvent e) {
		ActionEventExtended event = new ActionEventExtended(this,0,"PERSISTIR");
		this.actionListener.actionPerformed(event);
		System.out.println("Cerre");
		System.exit(0);
		
	}



	@Override
	public void windowClosed(WindowEvent e) {

		
	}



	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
