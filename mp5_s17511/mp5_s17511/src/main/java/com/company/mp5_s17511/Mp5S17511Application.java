package com.company.mp5_s17511;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.company.mp5_s17511.gui.controllers.MainWindowController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.company.mp5_s17511.model.Customer;
import com.company.mp5_s17511.model.Orders;
import com.company.mp5_s17511.model.Vehicle;
import com.company.mp5_s17511.model.Office;
import com.company.mp5_s17511.model.OfficeWorker;
import com.company.mp5_s17511.repository.BuildingRepo;
import com.company.mp5_s17511.repository.CustomerRepo;
import com.company.mp5_s17511.repository.ItemRepo;
import com.company.mp5_s17511.repository.OfficeRepo;
import com.company.mp5_s17511.repository.OfficeWorkerRepo;
import com.company.mp5_s17511.repository.OrderRepo;
import com.company.mp5_s17511.repository.ServiceWorkerRepo;
import com.company.mp5_s17511.repository.StaffRepo;
import com.company.mp5_s17511.repository.VehicleRepo;
import com.company.mp5_s17511.repository.WorkShopRepo;

import javax.swing.*;

@SpringBootApplication
public class Mp5S17511Application {

	public static void main(String[] args) {
	//	SpringApplication.run(Mp5S17511Application.class, args);

		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Mp5S17511Application.class)
				.headless(false).run(args);

		ctx.getBean(DataInitializer.class).initData();
		SwingUtilities.invokeLater(() -> {
			ctx.getBean(MainWindowController.class).showGUI();

		});


	}

}
