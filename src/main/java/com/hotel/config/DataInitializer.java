package com.hotel.config;

import com.hotel.model.*;
import com.hotel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 数据初始化类
 */
@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private DishRepository dishRepository;
    
    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private FoodOrderRepository foodOrderRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ParkingRecordRepository parkingRecordRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // 初始化客户数据
        if (customerRepository.count() == 0) {
            // 商务客户
            customerRepository.save(new Customer("张三", "13800138001", "zhangsan@example.com", "110101199001011234", "北京市朝阳区"));
            customerRepository.save(new Customer("李四", "13800138002", "lisi@example.com", "110101199002021234", "上海市浦东新区"));
            customerRepository.save(new Customer("王五", "13800138003", "wangwu@example.com", "110101199003031234", "广州市天河区"));
            customerRepository.save(new Customer("赵六", "13800138044", "zhaoliu@company.com", "110101198501151234", "深圳市南山区"));
            customerRepository.save(new Customer("钱七", "13800138045", "qianqi@tech.com", "110101198802201234", "杭州市西湖区"));
            customerRepository.save(new Customer("孙八", "13800138046", "sunba@finance.com", "110101199103251234", "成都市锦江区"));
            
            // 旅游客户
            customerRepository.save(new Customer("周九", "13800138047", "zhoujiu@travel.com", "110101199204301234", "西安市雁塔区"));
            customerRepository.save(new Customer("吴十", "13800138048", "wushi@vacation.com", "110101199306051234", "南京市鼓楼区"));
            customerRepository.save(new Customer("郑十一", "13800138049", "zhengshiyi@holiday.com", "110101199407101234", "武汉市江汉区"));
            customerRepository.save(new Customer("王十二", "13800138050", "wangshier@trip.com", "110101199508151234", "重庆市渝中区"));
            
            // 家庭客户
            customerRepository.save(new Customer("李十三", "13800138051", "lishisan@family.com", "110101198606201234", "天津市和平区"));
            customerRepository.save(new Customer("陈十四", "13800138052", "chenshisi@home.com", "110101198707251234", "青岛市市南区"));
            customerRepository.save(new Customer("刘十五", "13800138053", "liushiwu@family.com", "110101198808301234", "大连市中山区"));
            customerRepository.save(new Customer("张十六", "13800138054", "zhangshiliu@home.com", "110101198909041234", "厦门市思明区"));
            
            // VIP客户
            customerRepository.save(new Customer("赵十七", "13800138055", "zhaoshiqi@vip.com", "110101198010091234", "上海市黄浦区"));
            customerRepository.save(new Customer("孙十八", "13800138056", "sunshiba@premium.com", "110101197911141234", "北京市西城区"));
            customerRepository.save(new Customer("李十九", "13800138057", "lishijiu@luxury.com", "110101197812191234", "广州市越秀区"));
            
            // 国际客户
            customerRepository.save(new Customer("王二十", "13800138058", "wangershi@international.com", "110101197713241234", "上海市静安区"));
            customerRepository.save(new Customer("陈二一", "13800138059", "cheneryi@global.com", "110101197614291234", "北京市东城区"));
            customerRepository.save(new Customer("刘二二", "13800138060", "liuerer@world.com", "110101197515041234", "深圳市福田区"));
            
            // 常客
            customerRepository.save(new Customer("张二三", "13800138061", "zhangerer@regular.com", "110101197416091234", "杭州市上城区"));
            customerRepository.save(new Customer("赵二四", "13800138062", "zhaoersi@frequent.com", "110101197317141234", "成都市武侯区"));
            customerRepository.save(new Customer("孙二五", "13800138063", "sunerwu@loyal.com", "110101197218191234", "南京市建邺区"));
            
            // 团体客户
            customerRepository.save(new Customer("李二六", "13800138064", "lierliu@group.com", "110101197119241234", "武汉市武昌区"));
            customerRepository.save(new Customer("王二七", "13800138065", "wangerqi@team.com", "110101197020291234", "重庆市江北区"));
            customerRepository.save(new Customer("陈二八", "13800138066", "chenerba@corporate.com", "110101196921041234", "青岛市市北区"));
            
            // 年轻客户
            customerRepository.save(new Customer("刘二九", "13800138067", "liuerjiu@young.com", "110101199910091234", "西安市碑林区"));
            customerRepository.save(new Customer("张三十", "13800138068", "zhangsanshi@youth.com", "110101200011141234", "大连市西岗区"));
            customerRepository.save(new Customer("赵三一", "13800138069", "zhaosanyi@millennial.com", "110101200112191234", "厦门市湖里区"));
            
            // 老年客户
            customerRepository.save(new Customer("孙三二", "13800138070", "sansaner@senior.com", "110101195001011234", "天津市河西区"));
            customerRepository.save(new Customer("李三三", "13800138071", "lisansan@elder.com", "110101194902061234", "青岛市李沧区"));
            customerRepository.save(new Customer("王三四", "13800138072", "wangsansi@retired.com", "110101194803111234", "大连市沙河口区"));
            
            // 特殊需求客户
            customerRepository.save(new Customer("陈三五", "13800138073", "chensanwu@special.com", "110101199504161234", "上海市徐汇区"));
            customerRepository.save(new Customer("刘三六", "13800138074", "liusanliu@accessibility.com", "110101199405211234", "北京市海淀区"));
            customerRepository.save(new Customer("张三七", "13800138075", "zhangsanqi@assistance.com", "110101199306261234", "广州市荔湾区"));
        }
        
        // 初始化员工数据
        if (employeeRepository.count() == 0) {
            // 管理层
            employeeRepository.save(new Employee("张经理", "1001", "13800138007", "经理"));
            employeeRepository.save(new Employee("陈经理", "1002", "13800138008", "经理"));
            employeeRepository.save(new Employee("刘总监", "1003", "13800138009", "总监"));
            employeeRepository.save(new Employee("王主管", "1004", "13800138010", "主管"));
            
            // 前台接待
            employeeRepository.save(new Employee("赵七", "2123", "13800138006", "前台接待"));
            employeeRepository.save(new Employee("孙八", "2124", "13800138011", "前台接待"));
            employeeRepository.save(new Employee("周九", "2125", "13800138012", "前台接待"));
            
            // 客房服务
            employeeRepository.save(new Employee("李四", "2121", "13800138004", "服务员"));
            employeeRepository.save(new Employee("吴十", "2126", "13800138013", "服务员"));
            employeeRepository.save(new Employee("郑十一", "2127", "13800138014", "服务员"));
            employeeRepository.save(new Employee("王十二", "2128", "13800138015", "客房清洁"));
            employeeRepository.save(new Employee("李十三", "2129", "13800138016", "客房清洁"));
            
            // 餐饮部
            employeeRepository.save(new Employee("王六", "2122", "13800138005", "厨师"));
            employeeRepository.save(new Employee("陈十四", "2130", "13800138017", "厨师"));
            employeeRepository.save(new Employee("刘十五", "2131", "13800138018", "厨师"));
            employeeRepository.save(new Employee("张十六", "2132", "13800138019", "餐厅服务员"));
            employeeRepository.save(new Employee("赵十七", "2133", "13800138020", "餐厅服务员"));
            employeeRepository.save(new Employee("孙十八", "2134", "13800138021", "调酒师"));
            
            // 安保部
            employeeRepository.save(new Employee("李十九", "2135", "13800138022", "保安"));
            employeeRepository.save(new Employee("王二十", "2136", "13800138023", "保安"));
            employeeRepository.save(new Employee("陈二一", "2137", "13800138024", "保安队长"));
            
            // 工程部
            employeeRepository.save(new Employee("刘二二", "2138", "13800138025", "维修工"));
            employeeRepository.save(new Employee("张二三", "2139", "13800138026", "维修工"));
            employeeRepository.save(new Employee("赵二四", "2140", "13800138027", "电工"));
            
            // 财务部
            employeeRepository.save(new Employee("孙二五", "2141", "13800138028", "会计"));
            employeeRepository.save(new Employee("李二六", "2142", "13800138029", "出纳"));
            employeeRepository.save(new Employee("王二七", "2143", "13800138030", "财务经理"));
            
            // 人事部
            employeeRepository.save(new Employee("陈二八", "2144", "13800138031", "人事专员"));
            employeeRepository.save(new Employee("刘二九", "2145", "13800138032", "人事经理"));
            
            // 营销部
            employeeRepository.save(new Employee("张三十", "2146", "13800138033", "销售经理"));
            employeeRepository.save(new Employee("赵三一", "2147", "13800138034", "销售代表"));
            employeeRepository.save(new Employee("孙三二", "2148", "13800138035", "市场专员"));
            
            // 礼宾部
            employeeRepository.save(new Employee("李三三", "2149", "13800138036", "礼宾员"));
            employeeRepository.save(new Employee("王三四", "2150", "13800138037", "礼宾员"));
            employeeRepository.save(new Employee("陈三五", "2151", "13800138038", "礼宾主管"));
            
            // 康乐部
            employeeRepository.save(new Employee("刘三六", "2152", "13800138039", "健身教练"));
            employeeRepository.save(new Employee("张三七", "2153", "13800138040", "按摩师"));
            employeeRepository.save(new Employee("赵三八", "2154", "13800138041", "康乐经理"));
            
            // 已离职员工（用于测试）
            Employee resignedEmployee = new Employee("马三九", "2155", "13800138042", "服务员");
            resignedEmployee.setIsActive(false);
            employeeRepository.save(resignedEmployee);
            
            Employee resignedManager = new Employee("钱四十", "2156", "13800138043", "经理");
            resignedManager.setIsActive(false);
            employeeRepository.save(resignedManager);
        }
        
        // 初始化菜品数据
        if (dishRepository.count() == 0) {
            // 热菜
            dishRepository.save(new Dish("宫保鸡丁", "经典川菜，鸡肉丁配花生米", new BigDecimal("28.00"), "热菜", 50));
            dishRepository.save(new Dish("麻婆豆腐", "四川传统名菜，麻辣鲜香", new BigDecimal("18.00"), "热菜", 30));
            dishRepository.save(new Dish("红烧肉", "肥瘦相间，软糯香甜", new BigDecimal("35.00"), "热菜", 25));
            dishRepository.save(new Dish("糖醋里脊", "酸甜可口，外酥内嫩", new BigDecimal("32.00"), "热菜", 40));
            
            // 凉菜
            dishRepository.save(new Dish("凉拌黄瓜", "清爽开胃，解腻佳品", new BigDecimal("12.00"), "凉菜", 60));
            dishRepository.save(new Dish("口水鸡", "麻辣鲜香，肉质嫩滑", new BigDecimal("25.00"), "凉菜", 35));
            dishRepository.save(new Dish("蒜泥白肉", "肥而不腻，蒜香浓郁", new BigDecimal("22.00"), "凉菜", 30));
            
            // 汤品
            dishRepository.save(new Dish("西红柿鸡蛋汤", "家常汤品，营养丰富", new BigDecimal("15.00"), "汤品", 50));
            dishRepository.save(new Dish("冬瓜排骨汤", "清淡鲜美，滋补养生", new BigDecimal("28.00"), "汤品", 25));
            dishRepository.save(new Dish("紫菜蛋花汤", "简单易做，味道鲜美", new BigDecimal("12.00"), "汤品", 40));
            
            // 主食
            dishRepository.save(new Dish("白米饭", "优质大米，粒粒饱满", new BigDecimal("3.00"), "主食", 100));
            dishRepository.save(new Dish("蛋炒饭", "粒粒分明，香味浓郁", new BigDecimal("15.00"), "主食", 50));
            dishRepository.save(new Dish("面条", "手工制作，口感劲道", new BigDecimal("8.00"), "主食", 80));
            
            // 饮品
            dishRepository.save(new Dish("可乐", "冰镇可乐，清爽解腻", new BigDecimal("6.00"), "饮品", 100));
            dishRepository.save(new Dish("橙汁", "鲜榨橙汁，维C丰富", new BigDecimal("12.00"), "饮品", 50));
            dishRepository.save(new Dish("绿茶", "清香淡雅，回味甘甜", new BigDecimal("8.00"), "饮品", 60));
        }
        
        // 初始化房间数据
        if (roomRepository.count() == 0) {
            // 1楼 - 标准单人间
            roomRepository.save(new Room("101", RoomType.SINGLE, new BigDecimal("120.00"), "标准单人间，设施齐全，朝南"));
            roomRepository.save(new Room("102", RoomType.SINGLE, new BigDecimal("120.00"), "标准单人间，设施齐全，朝北"));
            roomRepository.save(new Room("103", RoomType.SINGLE, new BigDecimal("120.00"), "标准单人间，设施齐全，朝东"));
            roomRepository.save(new Room("104", RoomType.SINGLE, new BigDecimal("120.00"), "标准单人间，设施齐全，朝西"));
            roomRepository.save(new Room("105", RoomType.SINGLE, new BigDecimal("120.00"), "标准单人间，设施齐全，朝南"));
            
            // 1楼 - 标准双人间
            roomRepository.save(new Room("106", RoomType.DOUBLE, new BigDecimal("200.00"), "标准双人间，适合情侣，朝南"));
            roomRepository.save(new Room("107", RoomType.DOUBLE, new BigDecimal("200.00"), "标准双人间，适合情侣，朝北"));
            roomRepository.save(new Room("108", RoomType.DOUBLE, new BigDecimal("200.00"), "标准双人间，适合情侣，朝东"));
            roomRepository.save(new Room("109", RoomType.DOUBLE, new BigDecimal("200.00"), "标准双人间，适合情侣，朝西"));
            roomRepository.save(new Room("110", RoomType.DOUBLE, new BigDecimal("200.00"), "标准双人间，适合情侣，朝南"));
            
            // 2楼 - 标准单人间
            roomRepository.save(new Room("201", RoomType.SINGLE, new BigDecimal("120.00"), "标准单人间，设施齐全，朝南"));
            roomRepository.save(new Room("202", RoomType.SINGLE, new BigDecimal("120.00"), "标准单人间，设施齐全，朝北"));
            roomRepository.save(new Room("203", RoomType.SINGLE, new BigDecimal("120.00"), "标准单人间，设施齐全，朝东"));
            roomRepository.save(new Room("204", RoomType.SINGLE, new BigDecimal("120.00"), "标准单人间，设施齐全，朝西"));
            roomRepository.save(new Room("205", RoomType.SINGLE, new BigDecimal("120.00"), "标准单人间，设施齐全，朝南"));
            
            // 2楼 - 标准双人间
            roomRepository.save(new Room("206", RoomType.DOUBLE, new BigDecimal("200.00"), "标准双人间，适合情侣，朝南"));
            roomRepository.save(new Room("207", RoomType.DOUBLE, new BigDecimal("200.00"), "标准双人间，适合情侣，朝北"));
            roomRepository.save(new Room("208", RoomType.DOUBLE, new BigDecimal("200.00"), "标准双人间，适合情侣，朝东"));
            roomRepository.save(new Room("209", RoomType.DOUBLE, new BigDecimal("200.00"), "标准双人间，适合情侣，朝西"));
            roomRepository.save(new Room("210", RoomType.DOUBLE, new BigDecimal("200.00"), "标准双人间，适合情侣，朝南"));
            
            // 3楼 - 豪华单人间
            roomRepository.save(new Room("301", RoomType.SINGLE, new BigDecimal("180.00"), "豪华单人间，设施升级，朝南"));
            roomRepository.save(new Room("302", RoomType.SINGLE, new BigDecimal("180.00"), "豪华单人间，设施升级，朝北"));
            roomRepository.save(new Room("303", RoomType.SINGLE, new BigDecimal("180.00"), "豪华单人间，设施升级，朝东"));
            roomRepository.save(new Room("304", RoomType.SINGLE, new BigDecimal("180.00"), "豪华单人间，设施升级，朝西"));
            roomRepository.save(new Room("305", RoomType.SINGLE, new BigDecimal("180.00"), "豪华单人间，设施升级，朝南"));
            
            // 3楼 - 豪华双人间
            roomRepository.save(new Room("306", RoomType.DOUBLE, new BigDecimal("280.00"), "豪华双人间，设施升级，朝南"));
            roomRepository.save(new Room("307", RoomType.DOUBLE, new BigDecimal("280.00"), "豪华双人间，设施升级，朝北"));
            roomRepository.save(new Room("308", RoomType.DOUBLE, new BigDecimal("280.00"), "豪华双人间，设施升级，朝东"));
            roomRepository.save(new Room("309", RoomType.DOUBLE, new BigDecimal("280.00"), "豪华双人间，设施升级，朝西"));
            roomRepository.save(new Room("310", RoomType.DOUBLE, new BigDecimal("280.00"), "豪华双人间，设施升级，朝南"));
            
            // 4楼 - 商务套房
            roomRepository.save(new Room("401", RoomType.SUITE, new BigDecimal("450.00"), "商务套房，适合商务客人，朝南"));
            roomRepository.save(new Room("402", RoomType.SUITE, new BigDecimal("450.00"), "商务套房，适合商务客人，朝北"));
            roomRepository.save(new Room("403", RoomType.SUITE, new BigDecimal("450.00"), "商务套房，适合商务客人，朝东"));
            roomRepository.save(new Room("404", RoomType.SUITE, new BigDecimal("450.00"), "商务套房，适合商务客人，朝西"));
            roomRepository.save(new Room("405", RoomType.SUITE, new BigDecimal("450.00"), "商务套房，适合商务客人，朝南"));
            
            // 4楼 - 家庭套房
            roomRepository.save(new Room("406", RoomType.SUITE, new BigDecimal("380.00"), "家庭套房，适合家庭入住，朝南"));
            roomRepository.save(new Room("407", RoomType.SUITE, new BigDecimal("380.00"), "家庭套房，适合家庭入住，朝北"));
            roomRepository.save(new Room("408", RoomType.SUITE, new BigDecimal("380.00"), "家庭套房，适合家庭入住，朝东"));
            roomRepository.save(new Room("409", RoomType.SUITE, new BigDecimal("380.00"), "家庭套房，适合家庭入住，朝西"));
            roomRepository.save(new Room("410", RoomType.SUITE, new BigDecimal("380.00"), "家庭套房，适合家庭入住，朝南"));
            
            // 5楼 - 豪华套房
            roomRepository.save(new Room("501", RoomType.SUITE, new BigDecimal("680.00"), "豪华套房，空间宽敞，朝南"));
            roomRepository.save(new Room("502", RoomType.SUITE, new BigDecimal("680.00"), "豪华套房，空间宽敞，朝北"));
            roomRepository.save(new Room("503", RoomType.SUITE, new BigDecimal("680.00"), "豪华套房，空间宽敞，朝东"));
            roomRepository.save(new Room("504", RoomType.SUITE, new BigDecimal("680.00"), "豪华套房，空间宽敞，朝西"));
            roomRepository.save(new Room("505", RoomType.SUITE, new BigDecimal("680.00"), "豪华套房，空间宽敞，朝南"));
            
            // 6楼 - 总统套房
            roomRepository.save(new Room("601", RoomType.SUITE, new BigDecimal("1200.00"), "总统套房，顶级配置，朝南"));
            roomRepository.save(new Room("602", RoomType.SUITE, new BigDecimal("1200.00"), "总统套房，顶级配置，朝北"));
            roomRepository.save(new Room("603", RoomType.SUITE, new BigDecimal("1200.00"), "总统套房，顶级配置，朝东"));
            roomRepository.save(new Room("604", RoomType.SUITE, new BigDecimal("1200.00"), "总统套房，顶级配置，朝西"));
            roomRepository.save(new Room("605", RoomType.SUITE, new BigDecimal("1200.00"), "总统套房，顶级配置，朝南"));
            
            // 设置不同状态的房间
            // 已入住房间
            Room room101 = new Room("101", RoomType.SINGLE, new BigDecimal("120.00"), "标准单人间，设施齐全，朝南");
            room101.setStatus(RoomStatus.OCCUPIED);
            roomRepository.save(room101);
            
            Room room202 = new Room("202", RoomType.SINGLE, new BigDecimal("120.00"), "标准单人间，设施齐全，朝北");
            room202.setStatus(RoomStatus.OCCUPIED);
            roomRepository.save(room202);
            
            Room room306 = new Room("306", RoomType.DOUBLE, new BigDecimal("280.00"), "豪华双人间，设施升级，朝南");
            room306.setStatus(RoomStatus.OCCUPIED);
            roomRepository.save(room306);
            
            Room room401 = new Room("401", RoomType.SUITE, new BigDecimal("450.00"), "商务套房，适合商务客人，朝南");
            room401.setStatus(RoomStatus.OCCUPIED);
            roomRepository.save(room401);
            
            Room room501 = new Room("501", RoomType.SUITE, new BigDecimal("680.00"), "豪华套房，空间宽敞，朝南");
            room501.setStatus(RoomStatus.OCCUPIED);
            roomRepository.save(room501);
            
            // 已预订房间
            Room room102 = new Room("102", RoomType.SINGLE, new BigDecimal("120.00"), "标准单人间，设施齐全，朝北");
            room102.setStatus(RoomStatus.RESERVED);
            roomRepository.save(room102);
            
            Room room207 = new Room("207", RoomType.DOUBLE, new BigDecimal("200.00"), "标准双人间，适合情侣，朝北");
            room207.setStatus(RoomStatus.RESERVED);
            roomRepository.save(room207);
            
            Room room302 = new Room("302", RoomType.SINGLE, new BigDecimal("180.00"), "豪华单人间，设施升级，朝北");
            room302.setStatus(RoomStatus.RESERVED);
            roomRepository.save(room302);
            
            Room room402 = new Room("402", RoomType.SUITE, new BigDecimal("450.00"), "商务套房，适合商务客人，朝北");
            room402.setStatus(RoomStatus.RESERVED);
            roomRepository.save(room402);
            
            Room room601 = new Room("601", RoomType.SUITE, new BigDecimal("1200.00"), "总统套房，顶级配置，朝南");
            room601.setStatus(RoomStatus.RESERVED);
            roomRepository.save(room601);
            
            // 维护中房间
            Room room103 = new Room("103", RoomType.SINGLE, new BigDecimal("120.00"), "标准单人间，设施齐全，朝东");
            room103.setStatus(RoomStatus.MAINTENANCE);
            roomRepository.save(room103);
            
            Room room208 = new Room("208", RoomType.DOUBLE, new BigDecimal("200.00"), "标准双人间，适合情侣，朝东");
            room208.setStatus(RoomStatus.MAINTENANCE);
            roomRepository.save(room208);
            
            Room room303 = new Room("303", RoomType.SINGLE, new BigDecimal("180.00"), "豪华单人间，设施升级，朝东");
            room303.setStatus(RoomStatus.MAINTENANCE);
            roomRepository.save(room303);
            
            Room room403 = new Room("403", RoomType.SUITE, new BigDecimal("450.00"), "商务套房，适合商务客人，朝东");
            room403.setStatus(RoomStatus.MAINTENANCE);
            roomRepository.save(room403);
        }
        
        // 初始化停车位数据
        if (parkingSpotRepository.count() == 0) {
            System.out.println("初始化停车位数据...");
            
            // 地下车库A区 - 标准车位
            for (int i = 1; i <= 10; i++) {
                String spotNumber = String.format("A%02d", i);
                parkingSpotRepository.save(new ParkingSpot(spotNumber, ParkingSpotType.STANDARD, 
                    "地下车库A区", 5.0));
            }
            
            // 地下车库B区 - 紧凑车位
            for (int i = 1; i <= 8; i++) {
                String spotNumber = String.format("B%02d", i);
                ParkingSpot spot = new ParkingSpot(spotNumber, ParkingSpotType.COMPACT, 
                    "地下车库B区", 4.0);
                spot.setDescription("适合小型车");
                parkingSpotRepository.save(spot);
            }
            
            // 地下车库C区 - 大型车位
            for (int i = 1; i <= 6; i++) {
                String spotNumber = String.format("C%02d", i);
                ParkingSpot spot = new ParkingSpot(spotNumber, ParkingSpotType.LARGE, 
                    "地下车库C区", 7.0);
                spot.setDescription("适合SUV、商务车");
                parkingSpotRepository.save(spot);
            }
            
            // VIP车位
            for (int i = 1; i <= 4; i++) {
                String spotNumber = String.format("V%02d", i);
                ParkingSpot spot = new ParkingSpot(spotNumber, ParkingSpotType.VIP, 
                    "VIP专区", 10.0);
                spot.setDescription("贵宾专用车位，专人服务");
                parkingSpotRepository.save(spot);
            }
            
            // 充电车位
            for (int i = 1; i <= 3; i++) {
                String spotNumber = String.format("E%02d", i);
                ParkingSpot spot = new ParkingSpot(spotNumber, ParkingSpotType.ELECTRIC, 
                    "充电专区", 6.0);
                spot.setDescription("配备充电桩，支持快充");
                parkingSpotRepository.save(spot);
            }
            
            // 无障碍车位
            for (int i = 1; i <= 2; i++) {
                String spotNumber = String.format("D%02d", i);
                ParkingSpot spot = new ParkingSpot(spotNumber, ParkingSpotType.DISABLED, 
                    "无障碍专区", 5.0);
                spot.setDescription("专为残障人士设计，靠近电梯");
                parkingSpotRepository.save(spot);
            }
        }
        
        // 初始化预订数据
        if (reservationRepository.count() == 0) {
            System.out.println("初始化预订数据...");
            
            // 获取客户和房间数据
            List<Customer> customers = customerRepository.findAll();
            List<Room> rooms = roomRepository.findAll();
            
            if (!customers.isEmpty() && !rooms.isEmpty()) {
                // 已入住预订 - 对应房间状态为OCCUPIED
                // 101房间 - 标准单人间，已入住
                Room room101 = rooms.stream().filter(r -> "101".equals(r.getRoomNumber())).findFirst().orElse(null);
                if (room101 != null) {
                    Reservation reservation1 = new Reservation(customers.get(0), room101, 
                        LocalDate.now().minusDays(1), LocalDate.now().plusDays(2), new BigDecimal("360.00"), "商务出差，需要安静的房间");
                    reservation1.setStatus(ReservationStatus.CHECKED_IN);
                    reservation1.setCreateTime(LocalDateTime.now().minusDays(2));
                    reservationRepository.save(reservation1);
                }
                
                // 202房间 - 标准单人间，已入住
                Room room202 = rooms.stream().filter(r -> "202".equals(r.getRoomNumber())).findFirst().orElse(null);
                if (room202 != null) {
                    Reservation reservation2 = new Reservation(customers.get(1), room202, 
                        LocalDate.now(), LocalDate.now().plusDays(3), new BigDecimal("360.00"), "蜜月旅行，希望房间温馨");
                    reservation2.setStatus(ReservationStatus.CHECKED_IN);
                    reservation2.setCreateTime(LocalDateTime.now().minusDays(1));
                    reservationRepository.save(reservation2);
                }
                
                // 306房间 - 豪华双人间，已入住
                Room room306 = rooms.stream().filter(r -> "306".equals(r.getRoomNumber())).findFirst().orElse(null);
                if (room306 != null) {
                    Reservation reservation3 = new Reservation(customers.get(2), room306, 
                        LocalDate.now().minusDays(2), LocalDate.now().plusDays(1), new BigDecimal("840.00"), "家庭度假，需要豪华设施");
                    reservation3.setStatus(ReservationStatus.CHECKED_IN);
                    reservation3.setCreateTime(LocalDateTime.now().minusDays(3));
                    reservationRepository.save(reservation3);
                }
                
                // 401房间 - 商务套房，已入住
                Room room401 = rooms.stream().filter(r -> "401".equals(r.getRoomNumber())).findFirst().orElse(null);
                if (room401 != null) {
                    Reservation reservation4 = new Reservation(customers.get(3), room401, 
                        LocalDate.now().minusDays(1), LocalDate.now().plusDays(4), new BigDecimal("1800.00"), "重要商务会议，需要商务设施");
                    reservation4.setStatus(ReservationStatus.CHECKED_IN);
                    reservation4.setCreateTime(LocalDateTime.now().minusDays(2));
                    reservationRepository.save(reservation4);
                }
                
                // 501房间 - 豪华套房，已入住
                Room room501 = rooms.stream().filter(r -> "501".equals(r.getRoomNumber())).findFirst().orElse(null);
                if (room501 != null) {
                    Reservation reservation5 = new Reservation(customers.get(4), room501, 
                        LocalDate.now(), LocalDate.now().plusDays(2), new BigDecimal("1360.00"), "VIP客户，需要顶级服务");
                    reservation5.setStatus(ReservationStatus.CHECKED_IN);
                    reservation5.setCreateTime(LocalDateTime.now().minusDays(1));
                    reservationRepository.save(reservation5);
                }
                
                // 已预订房间 - 对应房间状态为RESERVED
                // 102房间 - 标准单人间，已预订
                Room room102 = rooms.stream().filter(r -> "102".equals(r.getRoomNumber())).findFirst().orElse(null);
                if (room102 != null) {
                    Reservation reservation6 = new Reservation(customers.get(5), room102, 
                        LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), new BigDecimal("240.00"), "明日入住，希望房间朝北");
                    reservation6.setStatus(ReservationStatus.CONFIRMED);
                    reservation6.setCreateTime(LocalDateTime.now().minusHours(2));
                    reservationRepository.save(reservation6);
                }
                
                // 207房间 - 标准双人间，已预订
                Room room207 = rooms.stream().filter(r -> "207".equals(r.getRoomNumber())).findFirst().orElse(null);
                if (room207 != null) {
                    Reservation reservation7 = new Reservation(customers.get(6), room207, 
                        LocalDate.now().plusDays(2), LocalDate.now().plusDays(5), new BigDecimal("600.00"), "情侣旅行，需要浪漫氛围");
                    reservation7.setStatus(ReservationStatus.CONFIRMED);
                    reservation7.setCreateTime(LocalDateTime.now().minusHours(1));
                    reservationRepository.save(reservation7);
                }
                
                // 302房间 - 豪华单人间，已预订
                Room room302 = rooms.stream().filter(r -> "302".equals(r.getRoomNumber())).findFirst().orElse(null);
                if (room302 != null) {
                    Reservation reservation8 = new Reservation(customers.get(7), room302, 
                        LocalDate.now().plusDays(3), LocalDate.now().plusDays(6), new BigDecimal("720.00"), "商务旅行，需要豪华单人间");
                    reservation8.setStatus(ReservationStatus.CONFIRMED);
                    reservation8.setCreateTime(LocalDateTime.now().minusDays(1));
                    reservationRepository.save(reservation8);
                }
                
                // 402房间 - 商务套房，已预订
                Room room402 = rooms.stream().filter(r -> "402".equals(r.getRoomNumber())).findFirst().orElse(null);
                if (room402 != null) {
                    Reservation reservation9 = new Reservation(customers.get(8), room402, 
                        LocalDate.now().plusDays(1), LocalDate.now().plusDays(4), new BigDecimal("1350.00"), "公司年会，需要商务套房");
                    reservation9.setStatus(ReservationStatus.CONFIRMED);
                    reservation9.setCreateTime(LocalDateTime.now().minusHours(3));
                    reservationRepository.save(reservation9);
                }
                
                // 601房间 - 总统套房，已预订
                Room room601 = rooms.stream().filter(r -> "601".equals(r.getRoomNumber())).findFirst().orElse(null);
                if (room601 != null) {
                    Reservation reservation10 = new Reservation(customers.get(9), room601, 
                        LocalDate.now().plusDays(5), LocalDate.now().plusDays(8), new BigDecimal("3600.00"), "重要客户，需要总统套房");
                    reservation10.setStatus(ReservationStatus.CONFIRMED);
                    reservation10.setCreateTime(LocalDateTime.now().minusDays(2));
                    reservationRepository.save(reservation10);
                }
                
                // 历史预订 - 已退房
                Room room104 = rooms.stream().filter(r -> "104".equals(r.getRoomNumber())).findFirst().orElse(null);
                if (room104 != null) {
                    Reservation reservation11 = new Reservation(customers.get(10), room104, 
                        LocalDate.now().minusDays(3), LocalDate.now().minusDays(1), new BigDecimal("240.00"), "已完成的商务出差");
                    reservation11.setStatus(ReservationStatus.CHECKED_OUT);
                    reservation11.setCreateTime(LocalDateTime.now().minusDays(4));
                    reservationRepository.save(reservation11);
                }
                
                Room room209 = rooms.stream().filter(r -> "209".equals(r.getRoomNumber())).findFirst().orElse(null);
                if (room209 != null) {
                    Reservation reservation12 = new Reservation(customers.get(11), room209, 
                        LocalDate.now().minusDays(5), LocalDate.now().minusDays(2), new BigDecimal("600.00"), "已完成的家庭旅行");
                    reservation12.setStatus(ReservationStatus.CHECKED_OUT);
                    reservation12.setCreateTime(LocalDateTime.now().minusDays(6));
                    reservationRepository.save(reservation12);
                }
                
                // 已取消的预订
                Room room105 = rooms.stream().filter(r -> "105".equals(r.getRoomNumber())).findFirst().orElse(null);
                if (room105 != null) {
                    Reservation reservation13 = new Reservation(customers.get(12), room105, 
                        LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), new BigDecimal("240.00"), "客户临时取消");
                    reservation13.setStatus(ReservationStatus.CANCELLED);
                    reservation13.setCreateTime(LocalDateTime.now().minusDays(1));
                    reservationRepository.save(reservation13);
                }
                
                Room room210 = rooms.stream().filter(r -> "210".equals(r.getRoomNumber())).findFirst().orElse(null);
                if (room210 != null) {
                    Reservation reservation14 = new Reservation(customers.get(13), room210, 
                        LocalDate.now().plusDays(2), LocalDate.now().plusDays(4), new BigDecimal("400.00"), "行程变更取消");
                    reservation14.setStatus(ReservationStatus.CANCELLED);
                    reservation14.setCreateTime(LocalDateTime.now().minusDays(2));
                    reservationRepository.save(reservation14);
                }
                
                // 未来预订 - 已确认
                Room room304 = rooms.stream().filter(r -> "304".equals(r.getRoomNumber())).findFirst().orElse(null);
                if (room304 != null) {
                    Reservation reservation15 = new Reservation(customers.get(14), room304, 
                        LocalDate.now().plusDays(7), LocalDate.now().plusDays(10), new BigDecimal("540.00"), "下周商务会议");
                    reservation15.setStatus(ReservationStatus.CONFIRMED);
                    reservation15.setCreateTime(LocalDateTime.now().minusDays(3));
                    reservationRepository.save(reservation15);
                }
                
                Room room406 = rooms.stream().filter(r -> "406".equals(r.getRoomNumber())).findFirst().orElse(null);
                if (room406 != null) {
                    Reservation reservation16 = new Reservation(customers.get(15), room406, 
                        LocalDate.now().plusDays(10), LocalDate.now().plusDays(15), new BigDecimal("1900.00"), "家庭度假计划");
                    reservation16.setStatus(ReservationStatus.CONFIRMED);
                    reservation16.setCreateTime(LocalDateTime.now().minusDays(5));
                    reservationRepository.save(reservation16);
                }
            }
        }
        
        // 初始化餐饮订单数据
        if (foodOrderRepository.count() == 0) {
            System.out.println("初始化餐饮订单数据...");
            
            // 获取客户、员工和菜品数据
            List<Customer> customers = customerRepository.findAll();
            List<Employee> employees = employeeRepository.findAll();
            List<Dish> dishes = dishRepository.findAll();
            
            if (!customers.isEmpty() && !employees.isEmpty() && !dishes.isEmpty()) {
                // 待确认订单
                FoodOrder order1 = new FoodOrder(customers.get(0), employees.get(0), "A01");
                order1.setTotalAmount(new BigDecimal("45.00"));
                order1.setStatus(OrderStatus.PENDING);
                order1.setCreateTime(LocalDateTime.now().minusMinutes(10));
                order1.setNotes("不要辣");
                foodOrderRepository.save(order1);
                
                // 已确认订单
                FoodOrder order2 = new FoodOrder(customers.get(1), employees.get(0), "A02");
                order2.setTotalAmount(new BigDecimal("78.00"));
                order2.setStatus(OrderStatus.CONFIRMED);
                order2.setCreateTime(LocalDateTime.now().minusMinutes(20));
                order2.setNotes("加急");
                foodOrderRepository.save(order2);
                
                // 制作中订单
                FoodOrder order3 = new FoodOrder(customers.get(2), employees.get(1), "B01");
                order3.setTotalAmount(new BigDecimal("95.00"));
                order3.setStatus(OrderStatus.PREPARING);
                order3.setCreateTime(LocalDateTime.now().minusMinutes(30));
                order3.setRoomNumber("101");
                order3.setNotes("挂房账");
                foodOrderRepository.save(order3);
                
                // 已完成订单
                FoodOrder order4 = new FoodOrder(customers.get(0), employees.get(1), "B02");
                order4.setTotalAmount(new BigDecimal("62.00"));
                order4.setStatus(OrderStatus.READY);
                order4.setCreateTime(LocalDateTime.now().minusMinutes(45));
                order4.setNotes("打包带走");
                foodOrderRepository.save(order4);
                
                // 已上菜订单
                FoodOrder order5 = new FoodOrder(customers.get(1), employees.get(0), "C01");
                order5.setTotalAmount(new BigDecimal("128.00"));
                order5.setStatus(OrderStatus.SERVED);
                order5.setCreateTime(LocalDateTime.now().minusMinutes(60));
                order5.setNotes("生日聚餐");
                foodOrderRepository.save(order5);
                
                // 已支付订单
                FoodOrder order6 = new FoodOrder(customers.get(2), employees.get(1), "C02");
                order6.setTotalAmount(new BigDecimal("156.00"));
                order6.setStatus(OrderStatus.PAID);
                order6.setIsPaid(true);
                order6.setPaymentMethod(PaymentMethod.CARD);
                order6.setCreateTime(LocalDateTime.now().minusHours(2));
                order6.setNotes("刷卡支付");
                foodOrderRepository.save(order6);
                
                // 已取消订单
                FoodOrder order7 = new FoodOrder(customers.get(0), employees.get(0), "D01");
                order7.setTotalAmount(new BigDecimal("35.00"));
                order7.setStatus(OrderStatus.CANCELLED);
                order7.setCreateTime(LocalDateTime.now().minusMinutes(15));
                order7.setNotes("客户临时取消");
                foodOrderRepository.save(order7);
                
                // 创建订单项数据
                createOrderItems(order1, dishes, Arrays.asList(1, 2), Arrays.asList(1, 2)); // 宫保鸡丁×1, 麻婆豆腐×2
                createOrderItems(order2, dishes, Arrays.asList(3, 4, 5), Arrays.asList(1, 1, 2)); // 红烧肉×1, 糖醋里脊×1, 凉拌黄瓜×2
                createOrderItems(order3, dishes, Arrays.asList(1, 6, 7, 8), Arrays.asList(2, 1, 1, 1)); // 宫保鸡丁×2, 口水鸡×1, 蒜泥白肉×1, 西红柿鸡蛋汤×1
                createOrderItems(order4, dishes, Arrays.asList(2, 9, 10), Arrays.asList(1, 1, 1)); // 麻婆豆腐×1, 冬瓜排骨汤×1, 紫菜蛋花汤×1
                createOrderItems(order5, dishes, Arrays.asList(1, 3, 6, 11, 12), Arrays.asList(1, 1, 1, 2, 1)); // 宫保鸡丁×1, 红烧肉×1, 口水鸡×1, 白米饭×2, 蛋炒饭×1
                createOrderItems(order6, dishes, Arrays.asList(1, 3, 4, 6, 7, 8, 9), Arrays.asList(2, 1, 1, 1, 1, 1, 1)); // 宫保鸡丁×2, 红烧肉×1, 糖醋里脊×1, 口水鸡×1, 蒜泥白肉×1, 西红柿鸡蛋汤×1, 冬瓜排骨汤×1
                createOrderItems(order7, dishes, Arrays.asList(1, 13), Arrays.asList(1, 1)); // 宫保鸡丁×1, 可乐×1
            }
        }
        
        // 初始化停车记录数据
        if (parkingRecordRepository.count() == 0) {
            System.out.println("初始化停车记录数据...");
            
            // 获取客户和停车位数据
            List<Customer> customers = customerRepository.findAll();
            List<ParkingSpot> parkingSpots = parkingSpotRepository.findAll();
            
            if (!customers.isEmpty() && !parkingSpots.isEmpty()) {
                // 进行中的停车记录（正在停车）
                ParkingRecord record1 = new ParkingRecord(customers.get(0), parkingSpots.get(0), "京A12345");
                record1.setEntryTime(LocalDateTime.now().minusHours(2));
                record1.setVehicleType("轿车");
                record1.setVehicleColor("白色");
                record1.setNotes("商务客人");
                record1.setStatus(ParkingRecordStatus.ACTIVE);
                parkingRecordRepository.save(record1);
                
                ParkingRecord record2 = new ParkingRecord(customers.get(1), parkingSpots.get(1), "沪B67890");
                record2.setEntryTime(LocalDateTime.now().minusMinutes(30));
                record2.setVehicleType("SUV");
                record2.setVehicleColor("黑色");
                record2.setNotes("家庭出行");
                record2.setStatus(ParkingRecordStatus.ACTIVE);
                parkingRecordRepository.save(record2);
                
                ParkingRecord record3 = new ParkingRecord(customers.get(2), parkingSpots.get(10), "粤C11111");
                record3.setEntryTime(LocalDateTime.now().minusMinutes(15));
                record3.setVehicleType("商务车");
                record3.setVehicleColor("银色");
                record3.setNotes("VIP客户");
                record3.setStatus(ParkingRecordStatus.ACTIVE);
                parkingRecordRepository.save(record3);
                
                // 已完成的停车记录（今日已完成）
                ParkingRecord record4 = new ParkingRecord(customers.get(0), parkingSpots.get(2), "京D22222");
                record4.setEntryTime(LocalDateTime.now().minusHours(4));
                record4.setExitTime(LocalDateTime.now().minusHours(1));
                record4.setDurationMinutes(180); // 3小时
                record4.setTotalFee(new BigDecimal("15.00")); // 5元/小时 × 3小时
                record4.setVehicleType("轿车");
                record4.setVehicleColor("红色");
                record4.setNotes("短时停车");
                record4.setStatus(ParkingRecordStatus.COMPLETED);
                parkingRecordRepository.save(record4);
                
                ParkingRecord record5 = new ParkingRecord(customers.get(1), parkingSpots.get(15), "沪E33333");
                record5.setEntryTime(LocalDateTime.now().minusHours(6));
                record5.setExitTime(LocalDateTime.now().minusHours(2));
                record5.setDurationMinutes(240); // 4小时
                record5.setTotalFee(new BigDecimal("28.00")); // 7元/小时 × 4小时
                record5.setVehicleType("SUV");
                record5.setVehicleColor("蓝色");
                record5.setNotes("购物停车");
                record5.setStatus(ParkingRecordStatus.COMPLETED);
                parkingRecordRepository.save(record5);
                
                // 昨日完成的停车记录
                ParkingRecord record6 = new ParkingRecord(customers.get(2), parkingSpots.get(20), "粤F44444");
                record6.setEntryTime(LocalDateTime.now().minusDays(1).minusHours(8));
                record6.setExitTime(LocalDateTime.now().minusDays(1).minusHours(2));
                record6.setDurationMinutes(360); // 6小时
                record6.setTotalFee(new BigDecimal("30.00")); // 5元/小时 × 6小时
                record6.setVehicleType("轿车");
                record6.setVehicleColor("灰色");
                record6.setNotes("过夜停车");
                record6.setStatus(ParkingRecordStatus.COMPLETED);
                parkingRecordRepository.save(record6);
                
                // VIP停车记录
                ParkingRecord record7 = new ParkingRecord(customers.get(0), parkingSpots.get(25), "京G55555");
                record7.setEntryTime(LocalDateTime.now().minusHours(1));
                record7.setVehicleType("豪华轿车");
                record7.setVehicleColor("金色");
                record7.setNotes("VIP贵宾，专人服务");
                record7.setStatus(ParkingRecordStatus.ACTIVE);
                parkingRecordRepository.save(record7);
                
                // 充电车位停车记录
                ParkingRecord record8 = new ParkingRecord(customers.get(1), parkingSpots.get(28), "沪H66666");
                record8.setEntryTime(LocalDateTime.now().minusMinutes(45));
                record8.setVehicleType("电动车");
                record8.setVehicleColor("白色");
                record8.setNotes("正在充电");
                record8.setStatus(ParkingRecordStatus.ACTIVE);
                parkingRecordRepository.save(record8);
                
                // 已取消的停车记录
                ParkingRecord record9 = new ParkingRecord(customers.get(2), parkingSpots.get(3), "粤I77777");
                record9.setEntryTime(LocalDateTime.now().minusMinutes(10));
                record9.setVehicleType("轿车");
                record9.setVehicleColor("绿色");
                record9.setNotes("客户临时取消");
                record9.setStatus(ParkingRecordStatus.CANCELLED);
                parkingRecordRepository.save(record9);
                
                // 超时停车记录
                ParkingRecord record10 = new ParkingRecord(customers.get(0), parkingSpots.get(4), "京J88888");
                record10.setEntryTime(LocalDateTime.now().minusHours(12));
                record10.setVehicleType("货车");
                record10.setVehicleColor("黄色");
                record10.setNotes("超时停车，需要处理");
                record10.setStatus(ParkingRecordStatus.OVERDUE);
                parkingRecordRepository.save(record10);
            }
        }
        
        // 初始化用户数据
        initializeUsers();
    }
    
    /**
     * 初始化用户数据
     */
    private void initializeUsers() {
        if (userRepository.count() == 0) {
            // 系统管理员
            User admin = new User("admin", "admin123", "系统管理员", UserRole.ADMIN);
            admin.setEmail("admin@royalgarden.com");
            admin.setPhone("13800000001");
            userRepository.save(admin);
            
            // 经理
            User manager = new User("manager", "manager123", "张经理", UserRole.MANAGER);
            manager.setEmail("manager@royalgarden.com");
            manager.setPhone("13800000002");
            userRepository.save(manager);
            
            // 前台1
            User frontDesk1 = new User("frontdesk1", "front123", "李前台", UserRole.FRONT_DESK);
            frontDesk1.setEmail("frontdesk1@royalgarden.com");
            frontDesk1.setPhone("13800000003");
            userRepository.save(frontDesk1);
            
            // 前台2
            User frontDesk2 = new User("frontdesk2", "front123", "王前台", UserRole.FRONT_DESK);
            frontDesk2.setEmail("frontdesk2@royalgarden.com");
            frontDesk2.setPhone("13800000004");
            userRepository.save(frontDesk2);
        }
    }
    
    /**
     * 创建订单项
     */
    private void createOrderItems(FoodOrder order, List<Dish> dishes, List<Integer> dishIndices, List<Integer> quantities) {
        for (int i = 0; i < dishIndices.size(); i++) {
            int dishIndex = dishIndices.get(i) - 1; // 转换为0基索引
            int quantity = quantities.get(i);
            
            if (dishIndex >= 0 && dishIndex < dishes.size()) {
                OrderItem orderItem = new OrderItem(order, dishes.get(dishIndex), quantity);
                orderItemRepository.save(orderItem);
            }
        }
    }
}
