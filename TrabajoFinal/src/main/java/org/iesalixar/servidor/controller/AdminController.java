package org.iesalixar.servidor.controller;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.iesalixar.servidor.dto.UsuarioDTO;
import org.iesalixar.servidor.models.Comercial;
import org.iesalixar.servidor.models.Customer;
import org.iesalixar.servidor.models.Office;
import org.iesalixar.servidor.models.OrderDetail;
import org.iesalixar.servidor.models.Store;
import org.iesalixar.servidor.models.Usuario;
import org.iesalixar.servidor.models.Vehicle;
import org.iesalixar.servidor.services.ComercialServiceImpl;
import org.iesalixar.servidor.services.CustomerServiceImpl;
import org.iesalixar.servidor.services.OfficeServiceImpl;
import org.iesalixar.servidor.services.OrderDetailServiceImpl;
import org.iesalixar.servidor.services.StoreServiceImpl;
import org.iesalixar.servidor.services.UsuarioServiceImpl;
import org.iesalixar.servidor.services.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UsuarioServiceImpl usuarioService;

	@Autowired
	CustomerServiceImpl customerService;

	@Autowired
	ComercialServiceImpl comercialService;

	@Autowired
	OrderDetailServiceImpl ordertlService;

	@Autowired
	StoreServiceImpl storeService;

	@Autowired
	OfficeServiceImpl officeService;
	
	@Autowired
	VehicleServiceImpl vehiculoService;

	@GetMapping
	public String home(@RequestParam(required = false, name = "cmrc") String cmrc, Model model, Authentication auth,
			HttpSession session) {

		// Obtener usuario por session
		String username = auth.getName();

		if (session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findUsuarioByUserName(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}

		List<Comercial> list_comercial = comercialService.getAllComercial();
		Comercial comercial = new Comercial();
		Comercial comercial_selected = null;

		// Obtener lista de clientes de los comerciales
		List<OrderDetail> orderDetail = ordertlService.getAllOrderDetails();
		HashSet<Customer> list_customer = new HashSet<>();

		if (cmrc != null) {
			comercial_selected = comercialService.getComercialById(Long.parseLong(cmrc));

			for (OrderDetail ord : orderDetail) {
				if (ord.getComercial().getId() == Long.parseLong(cmrc)) {
					list_customer.add(ord.getCustomer());
				}
			}

		} else {

			Usuario userDB = (Usuario) session.getAttribute("usuario");
			System.out.println(userDB);

			if (userDB.getRole().equals("ROLE_EMPLOYEE")) {
				System.out.println("Es empleado");
				System.out.println(userDB.getId());
				
				for (OrderDetail ord : orderDetail) {
					System.out.println(ord);
					System.out.println(ord.getComercial().getId());
					
					if (ord.getComercial().getId().equals(userDB.getId())) {
						
						list_customer.add(ord.getCustomer());
					}
					
				}

			} else {

				for (OrderDetail ord : orderDetail) {
					
					if (ord.getComercial().getId() == list_comercial.get(0).getId()) {
						list_customer.add(ord.getCustomer());
					}
				}
			}

		}
		
		System.out.println(list_customer);

		model.addAttribute("contenido", "INICIO");
		model.addAttribute("comerciales", list_comercial);
		model.addAttribute("comercial", comercial);
		model.addAttribute("cmrsl", comercial_selected);
		model.addAttribute("customers", list_customer);
		return "dashboard";
	}

	@PostMapping
	public String home(@ModelAttribute Comercial comercial, Model model) {

		return "redirect:/admin?cmrc=" + comercial.getId();
	}

	@GetMapping("/stores")
	public String storeGet(Model model) {

		List<Store> stores = storeService.getAllStores();

		model.addAttribute("stores", stores);

		return "admin/stores";

	}

	@GetMapping("/stores/cars")
	public String carStoreGet(@RequestParam(name = "id_store", required = false) String id_store, Model model) {

		Store storeDB = storeService.getStoreById(Long.parseLong(id_store));

		model.addAttribute("store", storeDB);

		return "admin/cars_store";

	}

	@GetMapping("/ficha")
	public String fichaGet(@RequestParam(name = "customer", required = false) String customer, Model model) {

		Customer ctmerDB = customerService.findCustomerById(Long.parseLong(customer));

		model.addAttribute("customer", ctmerDB);

		return "admin/ficha";

	}

	@GetMapping("/add-employee")
	public String addEmployeeGet(Model model) {

		UsuarioDTO dto = new UsuarioDTO();

		model.addAttribute("dto", dto);

		return "admin/register_employee";
	}

	@PostMapping("/add-employee")
	public String addEmployeePost(@ModelAttribute UsuarioDTO dto) {

		Comercial comercialDB = new Comercial();

		comercialDB.setNombre(dto.getNombre());
		comercialDB.setUserName(dto.getUsuario());
		comercialDB.setDni(dto.getDni());
		comercialDB.setAddreessLine(dto.getAddressLine());
		comercialDB.setApellidos(dto.getApellidos());
		comercialDB.setDni(dto.getDni());
		comercialDB.setEmail(dto.getEmail());
		comercialDB.setJobTitle("COMERCIAL");
		comercialDB.setRole("ROLE_EMPLOYEE");
		comercialDB.setPassword(new BCryptPasswordEncoder(15).encode(dto.getPassword()));
		comercialDB.setPhone(dto.getPhone());
		comercialDB.setSueldo(dto.getSueldo());
		comercialDB.setActivo(true);

		comercialService.insertComercial(comercialDB);

		return "redirect:/admin";
	}

	@GetMapping("/offices")
	public String officeGet(Model model) {

		List<Office> list_office = officeService.getAllOffices();

		model.addAttribute("offices", list_office);

		return "admin/offices";
	}

	@GetMapping("/offices/add")
	public String addOfficeGet(Model model) {

		Office dto = new Office();

		model.addAttribute("dto", dto);

		return "admin/addOffice";
	}

	@PostMapping("/offices/add")
	public String addOficePost(@ModelAttribute Office dto) {

		Office officeDB = dto;

		if (officeService.insertOffice(officeDB) != null) {

		}

		return "redirect:/admin/offices";
	}
	
	@GetMapping("/aprovePayment")
	public String paymentGet(@RequestParam(name="matricula", required = false) String matricula, @RequestParam(name="customer", required=false) String customer) {
		
		
		Vehicle carDB = vehiculoService.getVehicleByMatrucula(matricula);
		
		carDB.setStatus("SOLD");
		
		vehiculoService.updateVehicle(carDB);
		
		return "redirect:/admin/ficha?customer="+Long.parseLong(customer);
	}

}
