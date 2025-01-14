package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HuespedController;
import controller.ReservaController;
import modelo.Huesped;
import modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	private ReservaController reservaController;
	private HuespedController huespedController;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), tbReservas,
				null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		LlenarTablaReservas();

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), tbHuespedes,
				null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		LlenarTablaHuespedes();

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtBuscar.getText().isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
							"Por favor ingresa el apellido del cliente o el numero de la reserva que deseas encontrar");
				} else {
					limpiarTabla();
					llenarTablaHuespedesApellido();
					llenarTablaReservadId();
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuespedes = tbHuespedes.getSelectedRow();
				if (filaReservas >= 0) {

					int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea modificar la reservacion?");

					if (confirmar == JOptionPane.YES_OPTION) {

						Date fecha_E = (Date) tbReservas.getValueAt(filaReservas, 1);
						Date fecha_S = (Date) tbReservas.getValueAt(filaReservas, 2);
						String valor = (String) tbReservas.getValueAt(filaReservas, 3);
						String pago = (String) tbReservas.getValueAt(filaReservas, 4);
						String id = tbReservas.getValueAt(filaReservas, 0).toString();
						reservaController.Modificar(fecha_E, fecha_S, valor, pago, id);
						JOptionPane.showMessageDialog(contentPane, "Reservacion Modificada Exitosamente");
						limpiarTabla();
						LlenarTablaReservas();
						LlenarTablaHuespedes();
					}
				}

				if (filaHuespedes >= 0) {
					System.out.println("Desea modificar los datos");
					int confirmarH = JOptionPane.showConfirmDialog(null, "¿Desea modificar los datos?");

					if (confirmarH == JOptionPane.YES_OPTION) {
						String id = tbHuespedes.getValueAt(filaHuespedes, 0).toString();
						String name = tbHuespedes.getValueAt(filaHuespedes, 1).toString();
						String lName = tbHuespedes.getValueAt(filaHuespedes, 2).toString();
						Date bD = (Date) tbHuespedes.getValueAt(filaHuespedes, 3);
						String nacionalidad = tbHuespedes.getValueAt(filaHuespedes, 4).toString();
						String telefono = tbHuespedes.getValueAt(filaHuespedes, 5).toString();
						String idReserva = tbHuespedes.getValueAt(filaHuespedes, 6).toString();
						huespedController.Modificar(name, lName, bD, nacionalidad, telefono, idReserva, id);
						JOptionPane.showMessageDialog(contentPane, "Datos Modificados Exitosamente");
						limpiarTabla();
						LlenarTablaReservas();
						LlenarTablaHuespedes();

					}

				}
			}

		});

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuespedes = tbHuespedes.getSelectedRow();
				System.out.println(filaReservas);

				if (filaReservas >= 0) {
					int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea eliminar los datos?");

					if (confirmar == JOptionPane.YES_OPTION) {
						String id = tbReservas.getValueAt(filaReservas, 0).toString();
						reservaController.Eliminar(Integer.valueOf(id));
						JOptionPane.showMessageDialog(contentPane, "Registro Eliminado Exitosamente");
						limpiarTabla();
						LlenarTablaReservas();
						LlenarTablaHuespedes();
					}
				}

				if (filaHuespedes >= 0) {
					int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea eliminar los datos?");
					if (confirmar == JOptionPane.YES_OPTION) {
						String id = tbHuespedes.getValueAt(filaHuespedes, 0).toString();
						huespedController.Eliminar(Integer.valueOf(id));
						JOptionPane.showMessageDialog(contentPane, "Registro Eliminado Exitosamente");
						limpiarTabla();
						LlenarTablaReservas();
						LlenarTablaHuespedes();
					}

				}
			}
		});

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	public void LlenarTablaReservas() {
		List<Reserva> reserva = BuscarReservas();
		try {
			for (Reserva reservas : reserva) {
				modelo.addRow(new Object[] { reservas.getId(), reservas.getFechaE(), reservas.getFechaE(),
						reservas.getValor(), reservas.getPago() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void LlenarTablaHuespedes() {
		List<Huesped> huespedes = BuscarHuespedes();
		try {
			for (Huesped huesped : huespedes) {
				modeloH.addRow(
						new Object[] { huesped.getId(), huesped.getNombre(), huesped.getApellido(), huesped.getCumple(),
								huesped.getNacionalidad(), huesped.getTelefono(), huesped.getIdReserva() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Reserva> BuscarReservas() {
		return this.reservaController.buscar();
	}

	public List<Huesped> BuscarHuespedes() {
		return this.huespedController.buscar();
	}

	public void limpiarTabla() {
		((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
		((DefaultTableModel) tbHuespedes.getModel()).setRowCount(0);

	}

	public void llenarTablaHuespedesApellido() {
		List<Huesped> huespedesApellido = BuscarHuespedes(txtBuscar.getText());
		try {
			for (Huesped huesped : huespedesApellido) {
				modeloH.addRow(
						new Object[] { huesped.getId(), huesped.getNombre(), huesped.getApellido(), huesped.getCumple(),
								huesped.getNacionalidad(), huesped.getTelefono(), huesped.getIdReserva() });
			}
		} catch (Exception e) {
			throw e;
		}

	}

	public void llenarTablaReservadId() {
		List<Reserva> reservasId = BuscarReservas(txtBuscar.getText());
		try {
			for (Reserva reservas : reservasId) {
				modelo.addRow(new Object[] { reservas.getId(), reservas.getFechaE(), reservas.getFechaE(),
						reservas.getValor(), reservas.getPago() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private List<Reserva> BuscarReservas(String id) {
		return this.reservaController.buscar(id);
	}

	private List<Huesped> BuscarHuespedes(String apellido) {
		return this.huespedController.buscar(apellido);
	}
}
