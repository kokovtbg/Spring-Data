package jsonProcessing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jsonProcessing.carDealer.dto.*;
import jsonProcessing.carDealer.services.*;
import jsonProcessing.productsShop.dto.*;
import jsonProcessing.productsShop.services.CategoryService;
import jsonProcessing.productsShop.services.ProductService;
import jsonProcessing.productsShop.services.SeedService;
import jsonProcessing.productsShop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private Gson gson;
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
        this.gson = new GsonBuilder().setPrettyPrinting().create();
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

    private void getAllSales() throws IOException {
        List<SaleCarCustomerDTO> saleCarCustomerDTOs = this.saleService.getAll();
        Writer writer = new FileWriter("src/main/resources/files/carDealer/solutions/Query6_SalesWithAppliedDiscount.json");
        this.gson.toJson(saleCarCustomerDTOs, writer);
        writer.close();
    }

    private void getTotalSalesByCustomer() throws IOException {
        List<CustomerWithSaleDTO> customerWithSaleDTOs = this.customerService.getAll();
        Writer writer = new FileWriter("src/main/resources/files/carDealer/solutions/Query5_TotalSalesByCustomer.json");
        this.gson.toJson(customerWithSaleDTOs, writer);
        writer.close();
    }

    private void getCarsWithListParts() throws IOException {
        List<CarPartDTO> carPartDTOs = this.carService.getAll();
        Writer writer = new FileWriter("src/main/resources/files/carDealer/solutions/Query4_CarsWithTheirListParts.json");
        this.gson.toJson(carPartDTOs, writer);
        writer.close();
    }

    private void getLocalSuppliers() throws IOException {
        List<SupplierNotImporterDTO> supplierNotImporterDTOs = this.supplierService.getAllByImportPartsFalse();
        Writer writer = new FileWriter("src/main/resources/files/carDealer/solutions/Query3_LocalSuppliers.json");
        this.gson.toJson(supplierNotImporterDTOs, writer);
        writer.close();
    }

    private void getAllCarsFromMakeToyota() throws IOException {
        List<CarMakeDTO> carMakeDTOs = this.carService.getAllByMakeToyota();
        Writer writer = new FileWriter("src/main/resources/files/carDealer/solutions/Query2_CarsFromMakeToyota.json");
        this.gson.toJson(carMakeDTOs, writer);
        writer.close();
    }

    private void getAllCustomersSorted() throws IOException {
        List<CustomerWithoutSaleDTO> customerWithoutSaleDTOs = this.customerService.getAllSorted();
        Writer writer = new FileWriter("src/main/resources/files/carDealer/solutions/Query1_OrderedCustomers.json");
        this.gson.toJson(customerWithoutSaleDTOs, writer);
        writer.close();
    }

    private void carDealerImportData() throws IOException {
        this.importService.importAll();
    }

    private void getSellersWithSoldProducts() throws IOException {
        UserProductDTO userProductDTO = this.userService.getAll();
        Writer writer = new FileWriter("src/main/resources/files/productsShop/solutions/Query4_UsersAndProducts.json");
        this.gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        this.gson.toJson(userProductDTO, writer);
        writer.close();
    }

    private void getAllCategories() throws IOException {
        List<CategoryProductDTO> categoryProductDTOs = this.categoryService.getAllCategories();
        Writer writer = new FileWriter("src/main/resources/files/productsShop/solutions/Query3_CategoriesByProductsCount.json");
        this.gson.toJson(categoryProductDTOs, writer);
        writer.close();
    }

    private void getSellersWithSuccessfullySoldProducts() throws IOException {
        List<UserSoldProductDTO> userSoldProductDTOs = this.userService.getAllSorted();
        Writer writer = new FileWriter("src/main/resources/files/productsShop/solutions/Query2_SuccessfullySoldProducts.json");
        this.gson.toJson(userSoldProductDTOs, writer);
        writer.close();
    }

    private void getProductsInPriceRangeWithoutBuyer() throws IOException {
        List<ProductSellerDTO> productSellerDTOs = this.productService.getAllInPriceRangeWithoutBuyer();
        Writer writer = new FileWriter("src/main/resources/files/productsShop/solutions/Query1_ProductsInRange.json");
        this.gson.toJson(productSellerDTOs, writer);
        writer.close();
    }

    private void seedDatabase() throws IOException {
        this.seedService.seedAll();
    }
}
