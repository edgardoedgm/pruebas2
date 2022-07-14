package com.pruebas.models.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pruebas.models.entity.Registros;
import com.pruebas.models.services.IRegistroServices;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class RegistroRestController {

	@Autowired
	private IRegistroServices registroServices;

	@GetMapping("/registros")
	public List<Registros> index() {

		return registroServices.finsAll();
	}

	@GetMapping("/registros/{id}")
	public Registros consultaId(@PathVariable Long id) {

		return registroServices.findById(id);
	}

	@PostMapping("/mutant")
	@ResponseStatus(HttpStatus.CREATED)
	public Registros guardar(@RequestBody Registros registros) {

		ArrayList<String> respuesta = registros.getAdn();
		String[] adns = { "A", "T", "C", "G" };
		final int FILAS = 6, COLUMNAS = 6;
		String[][] mutante = new String[FILAS][COLUMNAS];
		int contador = 0;
		String str = String.join("", respuesta);

		for (int x = 0; x < FILAS; x++) {

			for (int y = 0; y < COLUMNAS; y++) {

				char c = str.charAt(contador);
				mutante[x][y] = Character.toString(c);
				contador = contador + 1;

			}

		}

		System.out.println("BusquedaH" + busquedaH(mutante, adns));

		registros.setFecha(new Date());
		return registroServices.Save(registros);
	}

	@PutMapping("/registros/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Registros modificar(@RequestBody Registros registros, @PathVariable Long id) {

		Registros resgistroactual = registroServices.findById(id);
		resgistroactual.setFecha(new Date());
		resgistroactual.setAdn(registros.getAdn());

		return registroServices.Save(resgistroactual);
	}

	@DeleteMapping("/registros/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {

		registroServices.delete(id);
	}

	public boolean busquedaH(String[][] entrada, String[] modelo) {
		for (int m = 0; m < modelo.length; m++) {
			int acun = 0;
			for (int x = 0; x < 6; x++) {

				for (int y = 0; y < 6; y++) {

					if (modelo[m].equals(entrada[x][y])) {

						acun = acun + 1;
					}
					System.out.print(entrada[x][y]);

				}
				System.out.println();
			}
			System.out.println(modelo[m] + " Repetido " + " " + acun);
		}

		return true;
	}

}
