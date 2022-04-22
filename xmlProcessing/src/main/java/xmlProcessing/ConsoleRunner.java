package xmlProcessing;

import xmlProcessing.carDealer.dto.*;
import xmlProcessing.carDealer.services.*;
import xmlProcessing.productsShop.dto.*;
import xmlProcessing.productsShop.services.CategoryService;
import xmlProcessing.productsShop.services.ProductService;
import xmlProcessing.productsShop.services.SeedService;
import xmlProcessing.productsShop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ImportService importService;
    private final SupplierService supplierService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    public ConsoleRunner(SeedService seedService,
                         UserService userService,
                         ProductService productService,
                         CategoryService categoryService,
                         ImportService importService,
                         SupplierService supplierService,
                         CarService carService,
                         CustomerService customerService,
                         SaleService saleService) {
        this.seedService = seedService;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.importService = importService;
        this.supplierService = supplierService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
//        1.	Products Shop
//        seedDatabase();//2.	Seed the Database
//        getProductsInPriceRangeWithoutBuyer();//Query 1 – Products in Range
//        getSellersWithSuccessfullySoldProducts();//Query 2 – Successfully Sold Products
//        getAllCategories();//Query 3 – Categories by Products Count
//        getSellersWithSoldProducts();//Query 4 – Users and Products

//        4.	Car Dealer
//        carDealerImportData();//5.	Car Dealer Import Data
//        getAllCustomersSorted();//Query 1 – Ordered Customers
//        getAllCarsFromMakeToyota();//Query 2 – Cars from Make Toyota
//        getLocalSuppliers();//Query 3 – Local Suppliers
//        getCarsWithListParts();//Query 4 – Cars with Their List of Parts
//        getTotalSalesByCustomer();//Query 5 – Total Sales by Customer
//        getAllSales();//Query 6 – Sales with Applied Discount
    }

    private void getAllSales() throws IOException, JAXBException {
        SalesCarCustomerDTO salesCarCustomerDTO = this.saleService.getAll();
        Writer writer = new FileWriter("src/main/resources/files/carDealer/solutions/Query6_SalesWithAppliedDiscount.xml");
        JAXBContext context = JAXBContext.newInstance(SalesCarCustomerDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(salesCarCustomerDTO, writer);
        writer.close();
    }

    private void getTotalSalesByCustomer() throws IOException, JAXBException {
        CustomersWithSaleDTO customersWithSaleDTO = this.customerService.getAll();
        Writer writer = new FileWriter("src/main/resources/files/carDealer/solutions/Query5_TotalSalesByCustomer.xml");
        JAXBContext context = JAXBContext.newInstance(CustomersWithSaleDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(customersWithSaleDTO, writer);
        writer.close();
    }

    private void getCarsWithListParts() throws IOException, JAXBException {
        CarsPartDTO carsPartDTO = this.carService.getAll();
        Writer writer = new FileWriter("src/main/resources/files/carDealer/solutions/Query4_CarsWithTheirListParts.xml");
        JAXBContext context = JAXBContext.newInstance(CarsPartDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(carsPartDTO, writer);
        writer.close();
    }

    private void getLocalSuppliers() throws IOException, JAXBException {
        SuppliersNotImporterDTO suppliersNotImporterDTO = this.supplierService.getAllByImportPartsFalse();
        Writer writer = new FileWriter("src/main/resources/files/carDealer/solutions/Query3_LocalSuppliers.xml");
        JAXBContext context = JAXBContext.newInstance(SuppliersNotImporterDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(suppliersNotImporterDTO, writer);
        writer.close();
    }

    private void getAllCarsFromMakeToyota() throws IOException, JAXBException {
        CarsMakeDTO carsMakeDTO = this.carService.getAllByMakeToyota();
        Writer writer = new FileWriter("src/main/resources/files/carDealer/solutions/Query2_CarsFromMakeToyota.xml");
        JAXBContext context = JAXBContext.newInstance(CarsMakeDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(carsMakeDTO, writer);
        writer.close();
    }

    private void getAllCustomersSorted() throws IOException, JAXBException {
        CustomersWithoutSaleDTO customersWithoutSaleDTO = this.customerService.getAllSorted();
        Writer writer = new FileWriter("src/main/resources/files/carDealer/solutions/Query1_OrderedCustomers.xml");
        JAXBContext context = JAXBContext.newInstance(CustomersWithoutSaleDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(customersWithoutSaleDTO, writer);
        writer.close();
    }

    private void carDealerImportData() throws IOException, JAXBException {
        this.importService.importAll();
    }

    private void getSellersWithSoldProducts() throws IOException, JAXBException {
        UsersAgeSoldProductDTO usersAgeSoldProductDTO = this.userService.getAll();
        Writer writer = new FileWriter("src/main/resources/files/productsShop/solutions/Query4_UsersAndProducts.xml");
        JAXBContext context = JAXBContext.newInstance(UsersAgeSoldProductDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(usersAgeSoldProductDTO, writer);
        writer.close();
    }

    private void getAllCategories() throws IOException, JAXBException {
        CategoriesProductDTO categoriesProductDTO = this.categoryService.getAllCategories();
        Writer writer = new FileWriter("src/main/resources/files/productsShop/solutions/Query3_CategoriesByProductsCount.xml");
        JAXBContext context = JAXBContext.newInstance(CategoriesProductDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(categoriesProductDTO, writer);
        writer.close();
    }

    private void getSellersWithSuccessfullySoldProducts() throws IOException, JAXBException {
        UsersSoldProductDTO usersSoldProductDTO = this.userService.getAllSorted();
        Writer writer = new FileWriter("src/main/resources/files/productsShop/solutions/Query2_SuccessfullySoldProducts.xml");
        JAXBContext context = JAXBContext.newInstance(UsersSoldProductDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(usersSoldProductDTO, writer);
        writer.close();
    }

    private void getProductsInPriceRangeWithoutBuyer() throws IOException, JAXBException {
        ProductsSellerDTO productsSellerDTO = this.productService.getAllInPriceRangeWithoutBuyer();
        Writer writer = new FileWriter("src/main/resources/files/productsShop/solutions/Query1_ProductsInRange.xml");
        JAXBContext context = JAXBContext.newInstance(ProductsSellerDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(productsSellerDTO, writer);
        writer.close();
    }

    private void seedDatabase() throws IOException, JAXBException {
        this.seedService.seedAll();
    }
}
