-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: database:3306
-- Tiempo de generación: 12-06-2024 a las 12:24:57
-- Versión del servidor: 8.3.0
-- Versión de PHP: 8.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `EverGrowth`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `id` bigint NOT NULL,
  `cantidad` int NOT NULL,
  `id_usuario` bigint NOT NULL,
  `id_producto` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `imagen` varchar(2048) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`, `imagen`) VALUES
(1, 'Aceites, especias y salsas', 'http://localhost:8085/media/aceite-removebg-preview.png'),
(2, 'Arroz, legumbres y pasta', 'http://localhost:8085/media/legumbres.jpeg'),
(3, 'Aperitivos', 'http://localhost:8085/media/gusanito-rojo-pegui-caja-25-kg.jpg'),
(4, 'Dulces y repostería', 'http://localhost:8085/media/historia_reposteria_francesa-1-removebg-preview.png'),
(5, 'Bebidas alcohólicas', 'http://localhost:8085/media/bebidas.jpg'),
(6, 'Carnes', 'http://localhost:8085/media/carnes.jpg'),
(7, 'Cereales', 'http://localhost:8085/media/cereales.jpg'),
(8, 'Charcutería y quesos', 'http://localhost:8085/media/tabla1-1080x675-removebg-preview.png'),
(9, 'Congelados y Pescado', 'http://localhost:8085/media/congelados.jpg'),
(10, 'Conservas, caldos y cremas', 'http://localhost:8085/media/vencerol-1-removebg-preview.png'),
(11, 'Frutas y verduras', 'http://localhost:8085/media/frutas%20y%20verduras.jpeg'),
(12, 'Huevos y lácteos', 'http://localhost:8085/media/descarga-removebg-preview.png'),
(13, 'Agua y refrescos', 'http://localhost:8085/media/refrescos-aguas-l-removebg-preview.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_pedido`
--

CREATE TABLE `detalle_pedido` (
  `id` bigint NOT NULL,
  `cantidad` int NOT NULL,
  `precio_unitario` float NOT NULL,
  `id_producto` bigint NOT NULL,
  `id_pedido` bigint NOT NULL,
  `iva` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `detalle_pedido`
--

INSERT INTO `detalle_pedido` (`id`, `cantidad`, `precio_unitario`, `id_producto`, `id_pedido`, `iva`) VALUES
(361, 2, 0.85, 4, 109, 0.05),
(362, 1, 0.35, 6, 109, 0),
(363, 1, 1.35, 105, 109, 0),
(364, 2, 1.75, 8, 109, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id` bigint NOT NULL,
  `fecha_pedido` datetime NOT NULL,
  `fecha_entrega` datetime NOT NULL,
  `estado_pedido` tinyint(1) NOT NULL,
  `id_usuario` bigint NOT NULL,
  `id_factura` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id`, `fecha_pedido`, `fecha_entrega`, `estado_pedido`, `id_usuario`, `id_factura`) VALUES
(109, '2024-06-12 12:23:26', '2024-06-15 12:23:26', 0, 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `precio` float NOT NULL,
  `descripcion` varchar(2048) NOT NULL,
  `stock` int NOT NULL,
  `imagen` varchar(2048) DEFAULT NULL,
  `id_categoria` bigint NOT NULL,
  `iva` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `precio`, `descripcion`, `stock`, `imagen`, `id_categoria`, `iva`) VALUES
(1, 'Aceite de oliva', 8, 'Botella 1 L | 8,00 €/L\n', 100, 'http://localhost:8085/media/Aceite-de-oliva-0-4-Hacendado-1-L.webp', 1, 0.05),
(2, 'Aceite de girasol', 1.45, 'Botella 1 L | 1,45 €/L\n', 100, 'http://localhost:8085/media/38334513c2db1608117eb6c2759439f2.jpg', 1, 0.05),
(3, 'Aceite de girasol', 6.75, 'Garrafa 5 L | 1,35 €/L\n', 100, 'http://localhost:8085/media/cebeaceb5dc29a1402256f693cd69829.webp', 1, 0.05),
(4, 'Vinagre de manzana', 0.85, 'Botella 1 L | 0,85 €/L\n', 98, 'http://localhost:8085/media/98d5a34a0d30798bbdc520993f63e542.jpg', 1, 0.05),
(5, 'Sal fina', 0.35, 'Paquete 1 kg | 0,35 €/kg\n', 100, 'http://localhost:8085/media/c8dad0074bbed02ff4c01a37b6ebe661.1500.0.0.0.wmark.7dc6a722.jpg', 1, 0),
(6, 'Sal gruesa', 0.35, 'Paquete 1 kg | 0,35 €/kg\n', 99, 'http://localhost:8085/media/8b9798cc6f23201e8191711f58368727.jpg', 1, 0),
(7, 'Mayonesa', 1.25, 'Bote 460 ml | 2,718 €/L\n', 100, 'http://localhost:8085/media/images.jpg', 1, 0),
(8, 'Chocolate negro fundir ', 1.75, 'Tableta 200 g | 8,75 €/kg\n', 98, 'http://localhost:8085/media/e8e32a0506510cdb96d0eb3211d9ac8c.jpg', 4, 0),
(9, 'Ketchup Heinz', 3.5, 'Bote 650 g | 5,385 €/kg\n', 100, 'http://localhost:8085/media/Ketchup-Heinz.jpg', 1, 0),
(10, 'Mostaza', 1.25, 'Tarro 200 g | 6,25 €/kg\n', 100, 'http://localhost:8085/media/4334494341409213b190f94f81e8baa5.jpg', 1, 0),
(11, 'Tomate frito', 2.3, 'Tarro 560 g | 4,108 €/kg\n', 100, 'http://localhost:8085/media/Tomate-frito-receta-artesana-Hacendado-con-aceite-de-oliva_600x.webp', 1, 0),
(12, 'Salsa pesto', 2.1, 'Tarro 190 g | 11,053 €/kg\n', 100, 'http://localhost:8085/media/0bb668dd6446fae242c2af59e4fa96a9.jpg', 1, 0),
(13, 'Salsa fresca Trufa', 1.45, 'Paquete 140 g | 10,358 €/kg\n', 100, 'http://localhost:8085/media/912baa300a06ed9f81d714338bda0d1d.jpg', 1, 0),
(14, 'Salsa fresca Queso', 1.45, 'Paquete 180 g | 8,056 €/kg\n', 100, 'http://localhost:8085/media/1e524f23891de8f06943ee50efcca5eb.jpg', 1, 0),
(15, 'Salsa fresca Setas', 1.65, 'Paquete 180 g | 9,167 €/kg\n', 100, 'http://localhost:8085/media/9360d51d50a894649e641c1e8b368626.1500.0.0.0.wmark.e1718de0.jpg', 1, 0),
(16, 'Agua mineral ', 1.26, 'Garrafa 6 L | 0,21 €/L\n', 100, 'http://localhost:8085/media/8b69a22c839c4e2c5816045bb9428843.jpg', 13, 0),
(17, 'Agua mineral', 2.34, '6 botellas x 1,5 L | 0,26 €/L\n', 100, 'http://localhost:8085/media/eda82b8b1f3b615a65f57985f96ce938.jpg', 13, 0),
(18, 'Agua mineral', 0.39, 'Botella 1,5 L | 0,26 €/L\n', 100, 'http://localhost:8085/media/b1860bb15ec2f38221beee0fed8296ca.jpg', 13, 0),
(19, 'Agua mineral', 4.34, '6 botellas x 1 L | 0,50 €/L\n', 100, 'http://localhost:8085/media/d57255409ef72ab939301f6af2ee8101%20(1).jpg', 13, 0),
(20, 'Agua mineral', 0.5, 'Botella 1 L | 0,50 €/L\n', 100, 'http://localhost:8085/media/c5eb5378be560301f298321f35f24071.jpg', 13, 0),
(21, 'Agua mineral', 1.62, '6 botellas x 500 ml | 0,54 €/L\n', 100, 'http://localhost:8085/media/e13d2747bd5d52c06a55b1b9f49688bc.jpg', 13, 0),
(22, 'Gaseosa ', 0.5, 'Botella 1,5 L | 0,334 €/L\n', 100, 'http://localhost:8085/media/5bb7872ed5fb0c428340f654f2bc9054.jpg', 13, 0),
(23, 'Tónica', 1.9, 'Botella 1 L | 1,90 €/L\n', 100, 'http://localhost:8085/media/00118613500059____5__600x600.jpg', 13, 0),
(24, 'Refresco té verde ', 0.9, 'Botella 1,5 L | 0,60 €/L\n', 100, 'http://localhost:8085/media/120213a015388287bf0e560e83790565.jpg', 13, 0),
(25, 'Aceitunas', 1, 'Bote 350 g (150 g escurrido) | 6,667 €/kg\n', 100, 'http://localhost:8085/media/cc1ca60ef2986c990f6f235afd216148.webp', 3, 0),
(26, 'Aceitunas sin hueso', 2.6, 'Tarro 835 g (400 g escurrido) | 6,50 €/kg\n', 100, 'http://localhost:8085/media/AceitunasSinHuesoHacendado.webp', 3, 0),
(27, 'Aceitunas negras', 1.05, 'Bote 350 g (150 g escurrido) | 3,00 €/kg\n', 100, 'http://localhost:8085/media/41-Aceitunas-negras-Hacendado-sin-hueso.webp', 3, 0),
(28, 'Pepinillos', 1.9, 'Tarro 530 g (290 g escurrido) | 6,552 €/kg\n', 100, 'http://localhost:8085/media/8bd1d925d2b6e161015f1df8f8079dd6.jpg', 3, 0),
(29, 'Nuez natural', 2, 'Paquete 200 g | 10,00 €/kg\n', 100, 'http://localhost:8085/media/de768af5358346867a912d57ec96b69c.jpg', 3, 0),
(30, 'Almendra natural', 2.05, 'Paquete 200 g | 10,25 €/kg\n', 100, 'http://localhost:8085/media/5e444ef0afdf7e21df5c31450462421a.1500.0.0.0.wmark.bdd691ff.jpg', 3, 0),
(31, 'Pistacho tostado ', 3.35, 'Paquete 250 g | 13,40 €/kg\n', 100, 'http://localhost:8085/media/f21c417db654f8b5b3bc884f2945ff17.1500.0.0.0.wmark.fa4d6d1e.jpg', 3, 0),
(32, 'Cacahuete frito ', 1.05, 'Paquete 250 g | 4,20 €/kg\n', 100, 'http://localhost:8085/media/07a2b46548d650635898f0b909dded94.jpg', 3, 0),
(33, 'Anacardo frito', 2.4, 'Paquete 200 g | 12,00 €/kg\n', 100, 'http://localhost:8085/media/c8d06999d4d13e85ea28af4d60ed49cb.jpg', 3, 0),
(34, 'Avellana tostada ', 3.1, 'Paquete 200 g | 15,50 €/kg\n', 100, 'http://localhost:8085/media/2fe6ffcc848dc8e5e28ed47bcec81a4b.jpg', 3, 0),
(35, 'Dátiles', 2.2, 'Paquete 350 g | 6,286 €/kg\n', 100, 'http://localhost:8085/media/748aca1ffbd304b26299c4727450422d.jpg', 3, 0),
(36, 'Patatas fritas', 1.3, 'Paquete 150 g | 8,667 €/kg\n', 100, 'http://localhost:8085/media/03-Patatas-fritas-extra-crujientes-Hacendado.webp', 3, 0),
(37, 'Arroz redondo', 1.27, 'Paquete 1 kg | 1,27 €/kg\n', 100, 'http://localhost:8085/media/0daf43fb5761b823ce83c985930c97c9.jpg', 2, 0),
(38, 'Arroz largo', 1.35, 'Paquete 1 kg | 1,35 €/kg\n', 100, 'http://localhost:8085/media/801f924df0d429d82c0a136901dcb9b0.jpg', 2, 0),
(39, 'Arroz bomba', 4.9, 'Paquete 1 kg | 4,90 €/kg\n', 100, 'http://localhost:8085/media/696d7c325a27f1bcc0b2244e7e3ecb2c.jpg', 2, 0),
(40, 'Arroz basmati ', 2.44, 'Paquete 1 kg | 2,44 €/kg\n', 100, 'http://localhost:8085/media/d25c062f26c257d9ca54b5fc219c2b62.jpg', 2, 0),
(41, 'Arroz vaporizado', 1.54, 'Paquete 1 kg | 1,54 €/kg\n', 100, 'http://localhost:8085/media/1c5a40f534247a5a8b8e16737c809be8.jpg', 2, 0),
(42, 'Garbanzo cocido', 0.71, 'Tarro 570 g (400 g escurrido) | 1,775 €/kg\n', 100, 'http://localhost:8085/media/5bda0a2e1f3bf01f853d8f6aca10ea41.1500.0.0.0.wmark.ba6d9e84.jpg', 2, 0),
(43, 'Garbanzo cocido ', 0.65, 'Tarro 295 g (210 g escurrido) | 3,096 €/kg\n', 100, 'http://localhost:8085/media/d83f87f3a99906695d95f67fc570a10a.jpg', 2, 0),
(44, 'Alubia blanca ', 2, 'Paquete 1 kg | 2,00 €/kg\n', 100, 'http://localhost:8085/media/16-Alubia-blanca-Hacendado-categori-a-extra.webp', 2, 0),
(45, 'Alubia negra', 2.4, 'Paquete 1 kg | 2,40 €/kg\n', 100, 'http://localhost:8085/media/17-Alubia-negra-Hacendado-categori-a-extra.webp', 2, 0),
(46, 'Alubia roja', 2.65, 'Paquete 1 kg | 2,65 €/kg\n', 100, 'http://localhost:8085/media/18-Alubia-roja-Hacendado-categori-a-extra_600x.webp', 2, 0),
(47, 'Lenteja pardina', 2.1, 'Paquete 1 kg | 2,10 €/kg\n', 100, 'http://localhost:8085/media/shopping.webp', 2, 0),
(48, 'Fideo grueso', 0.82, 'Paquete 500 g | 1,64 €/kg\n', 100, 'http://localhost:8085/media/0778f8e6272f1c743d1da88f16c4d978.jpg', 2, 0),
(49, 'Pajaritas vegetales ', 1, 'Paquete 500 g | 2,00 €/kg\n', 100, 'http://localhost:8085/media/efecccdb297479c7a16f0555a3375159.jpg', 2, 0),
(50, 'Pasta tiburón', 0.82, 'Paquete 500 g | 1,64 €/kg\n', 100, 'http://localhost:8085/media/f030b8a721c81c53f153399b85d72f4b.jpg', 2, 0),
(51, 'Tallarines', 0.82, 'Paquete 500 g | 1,64 €/kg\n', 100, 'http://localhost:8085/media/Tallarin-Hacendado.jpg', 2, 0),
(52, 'Spaghetti fino ', 0.82, 'Paquete 500 g | 1,64 €/kg\n', 100, 'http://localhost:8085/media/Spaghetti-fino-Hacendado.jpg', 2, 0),
(53, 'Azúcar blanco', 1.45, 'Paquete 1 kg | 1,45 €/kg\n', 100, 'http://localhost:8085/media/azucar-hacendado-600x600.webp', 4, 0),
(54, 'Azúcar moreno', 1.95, 'Paquete 1 kg | 1,95 €/kg\n', 100, 'http://localhost:8085/media/2568f7139beff545b68423bcee273137.jpg', 4, 0),
(55, 'Panela ', 1.95, 'Paquete 500 g | 3,90 €/kg\n', 100, 'http://localhost:8085/media/37828e8e0b311ebb395a481f35246b6f.jpg', 4, 0),
(56, 'Azúcar de Coco', 2.65, 'Paquete 300 g | 8,83 €/kg', 100, 'http://localhost:8085/media/7329774_001.jpg', 4, 0),
(57, 'Chocolate negro 85%', 1.45, 'Tableta 100 g | 14,50 €/kg\n', 100, 'http://localhost:8085/media/33-Chocolate-extrafino-negro-Hacendado-0-azu-cares-an-adidos-85-de-cacao-y-edulcorante_600x.webp', 4, 0),
(58, 'Chocolate Naranja ', 3.25, 'Tableta 100 Gr | 32,50 €/Kg', 100, 'http://localhost:8085/media/7045359_001.jpg', 4, 0),
(59, 'Chocolate Negro Postres', 1.75, 'Tableta 200 g | 8,75 €/kg', 100, 'http://localhost:8085/media/7108504_001.jpg', 4, 0),
(60, 'Conguitos', 2.63, 'Paquete 175 g | 15,37 €/kg', 100, 'http://localhost:8085/media/7294101_001.jpg', 4, 0),
(61, 'Mermelada Fresa', 2.86, 'Tarro 350 g | 8,54 €/kg', 100, 'http://localhost:8085/media/7164585_001.jpg', 4, 0),
(62, 'Mermelada de Higos', 2.99, 'Tarro 350 g | 8,54 €/kg', 100, 'http://localhost:8085/media/7183569_001.jpg', 4, 0),
(63, 'Mermelada Melocotón', 2.99, 'Tarro 350g | 8,54€/kg\n', 100, 'http://localhost:8085/media/7332971_001.jpg', 4, 0),
(64, 'Mermelada de Cereza', 2.99, 'Tarro 350g | 8,54€/kg\n', 100, 'http://localhost:8085/media/7403389_001.jpg', 4, 0),
(65, 'Miel Milflores', 3.2, 'Bote 500 g | 6,40 €/kg', 100, 'http://localhost:8085/media/7028280_001.jpg', 4, 0),
(66, 'Mermelada Melocotón', 1.4, 'Tarro 410 g | 3,41 €/kg', 100, 'http://localhost:8085/media/7055267_001.jpg', 4, 0),
(67, 'Mermelada Naranja', 1.25, 'Tarro 410 g | 3,05 €/kg', 100, 'http://localhost:8085/media/7055275_001.jpg', 4, 0),
(68, 'Cerveza Amstel', 5.52, '6 botellines x 250 ml | 1,84 €/L\n', 50, 'http://localhost:8085/media/00118602200299____4__600x600.jpg', 5, 0),
(69, 'Vermouth bianco', 10.2, 'Vermouth bianco Martini\nBotella 1 L | 10,20 €/L\n\n', 54, 'http://localhost:8085/media/martini-blanco.jpg', 5, 0),
(70, 'Ginebra Gin Beefeater ', 12.95, 'Botella 700 ml | 18,50 €/L\n', 79, 'http://localhost:8085/media/beefeater-london-dry-gin.jpg', 5, 0),
(71, 'Ginebra Gin Larios', 11.85, 'Botella 1 L | 11,85 €/L\n', 77, 'http://localhost:8085/media/images.jpg', 5, 0),
(72, 'Convidado de Baco', 5.85, 'Botella 1 L | 5,85 €/L\n', 66, 'http://localhost:8085/media/Bebida-espirituosa-Convidado-de-Baco.jpg', 5, 0),
(73, 'Whisky Cutty Sark', 11.55, 'Botella 700 ml | 16,50 €/L\n', 52, 'http://localhost:8085/media/00118721000489____4__600x600.jpg', 5, 0),
(74, 'Ron blanco', 5.25, 'Ron blanco superior La Recompensa\nBotella 700 ml | 7,50 €/L\n', 66, 'http://localhost:8085/media/7352533095f287fbbed8a63f7b7e5237.jpg', 5, 0),
(75, 'Piña colada', 5.8, 'Botella 700 ml | 8,286 €/L\n', 67, 'http://localhost:8085/media/75a230595c49d8295076f06d6df4cd58.jpg', 5, 0),
(76, 'Sidra asturiana', 1.6, 'Botella 750 ml | 2,134 €/L\n', 54, 'http://localhost:8085/media/Sidra_asturiana_el_mayu-removebg-preview.png', 5, 0),
(77, 'Cava brut Cabré & Sabaté', 1.65, 'Benjamín 200 ml | 8,25 €/L\n', 61, 'http://localhost:8085/media/Cava-brut-Cabre-Sabate.jpg', 5, 0),
(78, 'Vino blanco Chardonnay', 2.3, 'Botella 750 ml | 3,067 €/L\n', 65, 'http://localhost:8085/media/2e4828771d8e462dfbd8c29f98b7a56e.jpg', 5, 0),
(79, 'Vino blanco El Coto', 4.15, 'Botella 750 ml | 5,534 €/L\n', 80, 'http://localhost:8085/media/E6cbe755c83be4fecb4c9d9411db28d9dv.jpg', 5, 0),
(80, 'Vino rosado D.O Rioja', 2.4, 'Botella 750 ml | 3,20 €/L\n', 54, 'http://localhost:8085/media/680b62570c85021ac073c323d18440b0.jpg', 5, 0),
(81, 'Vino rosado Fidencio', 1.55, 'Botella 750 ml | 2,067 €/L\n', 67, 'http://localhost:8085/media/dcca62258af750a0d98b6deee30221b1.1500.0.0.0.wmark.5fc83234.jpg', 5, 0),
(82, 'Pechuga de Pollo', 8.6, 'Bandeja 600 g | 6,99 €/kg', 66, 'http://localhost:8085/media/140814_001.jpg', 6, 0),
(83, 'Pollo Limpio', 6.5, 'Bandeja 3,25 €/kg', 67, 'http://localhost:8085/media/218115_001.jpg', 6, 0),
(84, 'Carcasas de Pollo', 1.55, 'Bandeja 1 kg | 1,55 €/kg', 94, 'http://localhost:8085/media/224972_001.jpg', 6, 0),
(85, 'Solomillo de Cerdo', 4.4, 'Bandeja 500 g | 8,80 € / Kg', 55, 'http://localhost:8085/media/3339975_001.jpg', 6, 0),
(86, 'Chuleta Lomo de Cerdo', 5.09, 'Bandeja 550 g | ,59 € / Kg', 90, 'http://localhost:8085/media/3347499_001.jpg', 6, 0),
(87, 'Costilla de Cerdo Al Ajillo', 7.74, '10,99 € / Kg', 76, 'http://localhost:8085/media/7307213_001.jpg', 6, 0),
(88, 'Chorizo Extra Asturiano', 2.99, '11,96 € / Kg', 65, 'http://localhost:8085/media/7426901_001.jpg', 6, 0),
(89, 'Jamón Cocido', 1, 'Paquete 75 g | 13,33 € / Kg', 54, 'http://localhost:8085/media/7114929_001.jpg', 8, 0),
(90, 'Chistorra', 2.4, 'Paquete 200 g | 12,00 € / Kg', 100, 'http://localhost:8085/media/7047681_001.jpg', 8, 0),
(91, 'Jamón Serrano Reserva ', 2.69, 'Paquete 80 g | 33,63 € / Kg', 93, 'http://localhost:8085/media/7257280_001.jpg', 8, 0),
(92, 'Mortadela Ahumada', 2.15, 'Paquete 150 g | 14,33 € / Kg', 55, 'http://localhost:8085/media/7117018_001.jpg', 8, 0),
(93, 'Longaniza de Pavo', 2.69, 'Paquete 125g | 21,52 € / Kg', 65, 'http://localhost:8085/media/7365419_001.jpg', 8, 0),
(94, 'Longaniza Imperial', 2.95, 'Paquete 230 g | 11,80 € / Kg', 94, 'http://localhost:8085/media/231779_001.jpg', 8, 0),
(95, 'Chopped Lonchas', 1.71, 'Paquete 150 g | 11,40 € / Kg', 70, 'http://localhost:8085/media/7045300_001.jpg', 8, 0),
(96, 'Jamón Gran Reserva', 4.27, 'Paquete 250 g | 17,08 € / Kg', 54, 'http://localhost:8085/media/7069987_001.jpg', 8, 0),
(97, 'Filetes Pechuga de Pollo', 4.79, 'Bandeja 600 g | 7,95 € / Kg', 56, 'http://localhost:8085/media/601609_001.jpg', 6, 0),
(98, 'Longaniza ', 4.6, 'Bandeja 400 g | 11,49 € / Kg', 53, 'http://localhost:8085/media/1714799_001.jpg', 6, 0),
(99, 'Chuleta Lomo de Cerdo', 3.6, 'Bandeja 550 g | 6,59 € / Kg', 60, 'http://localhost:8085/media/3347499_001.jpg', 6, 0),
(100, 'Costilla de Cerdo', 4.72, 'Bandeja 600 g | 7,84 € / Kg', 70, 'http://localhost:8085/media/3347556_001.jpg', 6, 0),
(101, 'Carrillada de Cerdo', 5.5, 'Bandeja 400 g | 13,75 € / Kg', 52, 'http://localhost:8085/media/4532016_001_0016.jpg', 6, 0),
(102, 'Chuleta de Aguja', 4.18, 'Bandeja 650 g | 6,39 € / Kg', 67, 'http://localhost:8085/media/7018893_001.jpg', 6, 0),
(103, 'Magro Adobado', 2.99, 'Bandeja 330 g | 9,06 € / Kg', 81, 'http://localhost:8085/media/7231199_001.jpg', 6, 0),
(104, 'Lomo Adobado ', 3.29, 'Bandeja 450 g | 7,30 € / Kg', 53, 'http://localhost:8085/media/7237100_001.jpg', 6, 0),
(105, 'Copos de avena Brüggen', 1.35, 'Caja 800 g | 1,688 €/kg\n', 88, 'http://localhost:8085/media/edd41bd94e42f7bfb9c1e9f3b553e461.1500.0.0.0.wmark.80a04634.jpg', 7, 0),
(106, 'Muesli Crunchy con chocolate ', 2.1, 'Paquete 500 g | 4,20 €/kg\n', 65, 'http://localhost:8085/media/3d93c78bdee2295299fbdca9332e7439.jpg', 7, 0),
(107, 'Avena molida', 1.11, 'Paquete 500 g | 2,22 €/kg\n', 66, 'http://localhost:8085/media/ac09f887690d869a4c7d0ac036a412c6.jpg', 7, 0),
(108, 'Salvado de avena', 1.65, 'Paquete 500 g | 3,30 €/kg\n', 80, 'http://localhost:8085/media/bbb42fa3fd5ef29145d65b3f77c455b0.jpg', 6, 0),
(109, 'Cereales y semillas granola', 2.4, 'Paquete 400 g | 6,00 €/kg\n', 100, 'http://localhost:8085/media/7a44dff597160ef9d21af6bbc9524aa3.1500.0.0.0.wmark.9c989c82.jpg', 7, 0),
(110, 'Copos Arroz y Trigo', 1.93, 'Paquete 500 g | 3,90 €/kg\n', 54, 'http://localhost:8085/media/7044753_001.jpg', 7, 0),
(111, 'Copos de Maiz', 1.5, 'Paquete 500 g | 3,00 €/kg\n', 51, 'http://localhost:8085/media/7044746_001.jpg', 7, 0),
(112, 'Copos Avena Suave', 1.39, 'Paquete 500 g | 2,78 €/kg\n', 68, 'http://localhost:8085/media/7251507_001.jpg', 7, 0),
(113, 'Copos Avena Integral', 2.49, 'Paquete 500g | 4,98 €/kg', 56, 'http://localhost:8085/media/7347060_001.jpg', 7, 0),
(114, 'Trigo Integral Miel', 2.25, 'Paquete 375g | 6,00 €/kg\n', 50, 'http://localhost:8085/media/7394521_001.jpg', 7, 0),
(115, 'Cereales Fitness Original', 2.69, 'Paquete 300 g | 8,97 €/kg\n', 72, 'http://localhost:8085/media/7436330_001.jpg', 7, 0),
(116, 'Muesli Frutos Secos', 2.29, 'Paquete 500 g | 4,58 €/kg\n', 41, 'http://localhost:8085/media/7391717_001.jpg', 7, 0),
(117, 'Muesli sin Azúcar', 4.89, 'Paquete 560 g | 8,73 €/kg\n', 87, 'http://localhost:8085/media/2715118_001.jpg', 7, 0),
(118, 'Muesli Fruta y Fibra', 1.95, 'Paquete 500 g | 4,33 €/kg\n', 51, 'http://localhost:8085/media/7044774_001.jpg', 7, 0),
(119, 'Queso Mozzarella Lonchas', 2.74, 'Paquete 150 g | 18,27 €/kg', 55, 'http://localhost:8085/media/7068260_001.jpg', 8, 0),
(120, 'Queso Cheddar Lonchas', 6.71, 'Paquete 150 g | 15,67 €/kg', 18, 'http://localhost:8085/media/7260649_001.jpg', 8, 0),
(121, 'Queso Maasdam Lonchas', 2.59, 'Paquete 200 g | 12,95 €/kg', 65, 'http://localhost:8085/media/7363401_001.jpg', 8, 0),
(122, 'Queso Rallado Emmental', 1.58, 'Paquete 200 g | 7,90 €/kg', 51, 'http://localhost:8085/media/7036150_001.jpg', 8, 0),
(123, 'Queso Tierno Lonchas', 1.88, 'Paquete 200 g | 9,40 € / Kg', 49, 'http://localhost:8085/media/7142052_001.jpg', 8, 0),
(124, 'Queso Semi Lonchas', 2.32, 'Paquete 200 g | 11,60 €/kg\n', 80, 'http://localhost:8085/media/7142057_001.jpg', 8, 0),
(125, 'Lomos de Merluza ', 6.29, 'Paquete 360 g | 17,48 €/kg\n', 51, 'http://localhost:8085/media/216713_001.jpg', 9, 0),
(126, 'Anillos a la Romana ', 2.95, 'Paquete 400 g | 7,38  €/kg\n', 70, 'http://localhost:8085/media/5524400_001.jpg', 9, 0),
(127, 'Guisantes Finos', 3.19, 'Paquete 800 g | 3,99€/kg\n', 48, 'http://localhost:8085/media/7379584_001.jpg', 9, 0),
(128, 'Ensaladilla ', 3.19, 'Paquete 800 g | 3,99 €/kg\n', 68, 'http://localhost:8085/media/7379576_001.jpg', 9, 0),
(129, 'Almeja del Pacífico', 1.99, 'Paquete 500 g | 3,98 €/kg\n', 56, 'http://localhost:8085/media/7423606_001.jpg', 9, 0),
(130, 'Habas Baby', 4.99, 'Paquete 400 g | 12,48 €/kg\n', 40, 'http://localhost:8085/media/216275_001.jpg', 9, 0),
(131, 'Nata Montada', 2.1, 'Bote 500 ml | 9,13 €/kg', 70, 'http://localhost:8085/media/7062581_001.jpg', 9, 0),
(132, 'Hielo', 0.95, 'Bolsa 2kg | 0,48 € / Kg', 55, 'http://localhost:8085/media/7102655_001.jpg', 9, 0),
(133, 'Ajos Tiernos', 1.89, 'Paquete 300 g | 6,30 €/kg\n', 100, 'http://localhost:8085/media/7203297_001.jpg', 9, 0),
(134, 'Puntilla Enharinada', 3.49, 'Paquete 300 g escurrido | 11,63 €/kg\n', 53, 'http://localhost:8085/media/7204964_001.jpg', 9, 0),
(135, 'Brócoli ', 1.95, 'Paquete 900 g escurrido | 2,17 €/kg\n', 100, 'http://localhost:8085/media/7219483_001.jpg', 9, 0),
(136, 'Habas Finas', 1.15, 'Paquete 400 g escurrido | 2,88 €/kg\n', 55, 'http://localhost:8085/media/7219488_001.jpg', 9, 0),
(137, 'Cebolla Troceada', 1, 'Paquete 400 g escurrido | 2,50 €/kg\n', 54, 'http://localhost:8085/media/7219491_001.jpg', 9, 0),
(138, 'Alcachofa Troceada', 2.55, 'Paquete 400 g escurrido | 6,38 €/kg\n', 72, 'http://localhost:8085/media/7219504_001.jpg', 9, 0),
(139, 'Guisantes Finos', 1.69, 'Paquete 1 Kg escurrido | 1,69 €/kg\n', 100, 'http://localhost:8085/media/7219512_001.jpg', 9, 0),
(140, 'Garbanzos ', 1.19, 'Paquete 400 g escurrido | 2,98 €/kg\n', 100, 'http://localhost:8085/media/7219566_001.jpg', 9, 0),
(141, 'Judías Plana', 1.55, 'Paquete 1 Kg escurrido | 1,55 €/kg\n', 100, 'http://localhost:8085/media/7220643_001.jpg', 9, 0),
(142, 'Solomillo de cerdo marinado', 6.24, 'Paquete 800 g aprox. | 7,80 €/kg\n', 100, 'http://localhost:8085/media/72c56d09663789656e07eb71e457b3fe.jpg', 9, 0),
(143, 'Gamba pelada cruda', 4.95, 'Paquete 360 g escurrido | 13,75 €/kg\n', 100, 'http://localhost:8085/media/f3db1e076702d2ec8c9dbedd0f56f044.jpg', 9, 0),
(144, 'Colas de gambón crudo', 6.55, 'Bandeja 250 g | 26,20 €/kg\n', 100, 'http://localhost:8085/media/74f4d34dde795c85c5611232e96f264b.jpg', 9, 0),
(145, 'Filetes de merluza', 5.1, 'Paquete 600 g escurrido | 8,50 €/kg\n', 74, 'http://localhost:8085/media/fc66b5c0cfb6592e430bd5ead8db0337.jpg', 9, 0),
(146, 'Tubo de pota', 1.29, 'Pieza 100 g aprox. escurrido | 12,90 €/kg\n', 56, 'http://localhost:8085/media/4cbb284beda3ccf070e93226a3e64754.jpg', 9, 0),
(147, 'Filete de salmón', 3.58, 'Pieza 300 g aprox. escurrido | 11,95 €/kg\n', 66, 'http://localhost:8085/media/b1779d3f856f82ee02e7f7afd8070529.jpg', 9, 0),
(148, 'Tiras de potón', 4.45, 'Paquete 400 g escurrido | 11,125 €/kg\n', 100, 'http://localhost:8085/media/4fdab476ef08c8dc8e5af196af075723.jpg', 9, 0),
(149, 'Brócoli ', 2.05, 'Paquete 1 kg | 2,05 €/kg\n', 100, 'http://localhost:8085/media/Brocoli-Hacendado-ultracongelado.jpg', 9, 0),
(150, 'Queso Mozzarella', 0.95, 'Paquete 125 g | 7,60  €/kg\n', 51, 'http://localhost:8085/media/7173094_001.jpg', 8, 0),
(151, 'Caldo Casero Pescado', 2.2, 'Botella 1 L | 2,20 €/L\n', 90, 'http://localhost:8085/media/7188217_001.jpg', 10, 0),
(152, 'Caldo Casero Pollo', 2.2, 'Botella 1 L | 2,20 €/L\n', 100, 'http://localhost:8085/media/7187813_001.jpg', 10, 0),
(153, 'Puré de Patatas', 2.29, 'Paquete 230 g | 9,96 € / Kg', 69, 'http://localhost:8085/media/300673_001.jpg', 10, 0),
(154, 'Crema de Verduras ', 2.29, 'Botella 0,5 L | 4,58 €/L\n', 100, 'http://localhost:8085/media/7056724_001.jpg', 10, 0),
(155, 'Crema de Calabaza', 2.29, 'Botella  0,5  L | 4,58 €/L\n', 59, 'http://localhost:8085/media/7227788_001.jpg', 10, 0),
(156, 'Atún en aceite de girasol', 4.2, '6 latas x 60 g | 11,667 €/kg\n', 82, 'http://localhost:8085/media/06-Atu-n-claro-en-aceite-de-girasol-Hacendado-3-latas-de-80g.webp', 10, 0),
(157, 'Atún en aceite de oliva', 8, 'Lata 900 g (650 g escurrido) | 12,308 €/kg\n', 76, 'http://localhost:8085/media/10-Atu-n-en-aceite-de-oliva-Hacendado-lata-900g.webp', 10, 0),
(158, 'Espárragos muy gruesos', 3, 'Tarro 540 g (325 g escurrido) | 9,231 €/kg\n', 55, 'http://localhost:8085/media/daf7cd04c16c5c333256c0cb8a1fda7a.jpg', 10, 0),
(159, 'Pimientos del piquillo ', 1.45, 'Tarro 340 g (275 g escurrido) | 5,273 €/kg\n', 46, 'http://localhost:8085/media/4809a31c1a159e97cf927b4f4673d0e0.jpg', 10, 0),
(160, 'Judías verdes redondas', 1.3, 'Tarro 660 g (360 g escurrido) | 3,612 €/kg\n', 59, 'http://localhost:8085/media/6530550e61cb49e23deb6ce08d759eb8.jpg', 10, 0),
(161, 'Melocotón en almíbar', 2.05, 'Bote 840 g (480 g escurrido) | 4,271 €/kg\n', 100, 'http://localhost:8085/media/19-Melocoto-n-en-almi-bar-Hacendado.webp', 10, 0),
(162, 'Salmorejo ', 1.7, 'Botella 1 L | 1,70 €/L\n', 100, 'http://localhost:8085/media/Salmorejo-al-estilo-cordobes-Hacendado.jpg', 10, 0),
(163, 'Sopa de pollo', 0.85, 'Sobre 71 g | 11,972 €/kg\n', 100, 'http://localhost:8085/media/images.jpg', 10, 0),
(164, 'Sopa maravilla', 0.65, 'Sobre 85 g | 7,648 €/kg\n', 66, 'http://localhost:8085/media/bc507cef97af6e14a89330bf4bb0f5a1.jpg', 10, 0),
(165, 'Tomate triturado', 0.98, 'Bote 800 g | 1,225 €/kg\n', 100, 'http://localhost:8085/media/117a2a2230b103f17b50e07a73a8fc38.jpg', 10, 0),
(166, 'Banana', 0.26, 'Pieza 180 g aprox. | 1,45 €/kg\n', 100, 'http://localhost:8085/media/Fresh-Banana-Fruit-Each_5939a6fa-a0d6-431c-88c6-b4f21608e4be.f7cd0cc487761d74c69b7731493c1581.webp', 11, 0),
(167, 'Uva blanca', 2.5, 'Bandeja 500 g aprox. | 4,99 €/kg\n', 55, 'http://localhost:8085/media/Uva-blanca-sin-semillas.jpg', 11, 0),
(168, 'Manzanas Golden', 2.99, 'Bolsa 1,5 kg aprox. | 1,99 €/kg\n', 85, 'http://localhost:8085/media/fff0b633705b0eb0bc77f9a5e1d45b3c.jpg', 11, 0),
(169, 'Manzanas rojas', 3.21, 'Bolsa 1,5 kg aprox. | 2,14 €/kg\n', 100, 'http://localhost:8085/media/72af5bfc30fcadef171074b4e1a0f420.jpg', 11, 0),
(170, 'Limones', 1.79, 'Malla 1 kg | 1,79 €/kg\n', 100, 'http://localhost:8085/media/1172aaa39919acb2929d30dda6942352.jpg', 11, 0),
(171, 'Naranjas', 4.47, 'Malla 3 kg | 1,49 €/kg\n', 58, 'http://localhost:8085/media/images%20(1).jpg', 11, 0),
(172, 'Kiwis verdes', 4.1, '1 kg aprox. | 4,10 €/kg\n', 68, 'http://localhost:8085/media/Kiwi-cesta-1-kg-768x451.jpg', 11, 0),
(173, 'Piña', 3.32, 'Pieza 1,74 kg aprox. | 1,91 €/kg\n', 54, 'http://localhost:8085/media/pi%C3%B1a_g.jpg', 11, 0),
(174, 'Lechuga Iceberg', 0.99, 'Pieza 1 ud. | 0,99 €/ud\n', 46, 'http://localhost:8085/media/images%20(2).jpg', 11, 0),
(175, 'Lechuga corazón romana', 1.15, 'Paquete 1 ud. | 1,15 €/ud\n', 77, 'http://localhost:8085/media/Lechuga-corazon-romana.jpg', 11, 0),
(176, 'Patatas para freir', 3.75, 'Malla 2 kg | 1,875 €/kg\n', 96, 'http://localhost:8085/media/b1cececc091434e8adde6ce44c92e079.jpg', 11, 0),
(177, 'Patatas', 4.9, 'Malla 3 kg | 1,634 €/kg\n', 84, 'http://localhost:8085/media/f6cc8ef3532225e5f557e79c5f6cf3f1.jpg', 11, 0),
(178, 'Cebollas', 2.79, 'Malla 2 kg | 1,395 €/kg\n', 100, 'http://localhost:8085/media/64160afe890b9ced7ac438767a0103aa.jpeg', 11, 0),
(179, 'Tomates', 3.3, 'Malla 2 kg | 1,65 €/kg\n', 81, 'http://localhost:8085/media/1f7c573e8ca26a34766e270bd2bb765e.jpg', 11, 0),
(180, 'Pepino', 0.3, 'Pieza 230 g aprox. | 1,29 €/kg\n', 57, 'http://localhost:8085/media/images%20(3).jpg', 11, 0),
(181, 'Calabacín verde', 0.68, 'Pieza 380 g aprox. | 1,79 €/kg\n', 56, 'http://localhost:8085/media/ebbfb6a904fe02ba5a88b02f2be911fd.jpg', 11, 0),
(182, 'Calabacín blanco', 0.72, 'Pieza 360 g aprox. | 1,99 €/kg\n', 73, 'http://localhost:8085/media/50a267029cedf70b9f9a52c389deb4bc.jpg', 11, 0),
(183, 'Berenjena', 0.68, 'Pieza 360 g aprox. | 1,89 €/kg\n', 100, 'http://localhost:8085/media/cbd784174d4dfee4fedee9f32d3ad0ab.jpg', 11, 0),
(184, 'Puerros', 1.68, 'Manojo 2 ud. | 2,80 €/kg\n', 100, 'http://localhost:8085/media/4822d3f064b8368fdcaa531b5339f14d.jpg', 11, 0),
(185, 'Zanahoria ', 1.09, 'Paquete 1 kg | 1,09 € / Kg', 53, 'http://localhost:8085/media/4501508_001.jpg', 11, 0),
(186, 'Mango', 2, '3,99 € / 1 Kg', 80, 'http://localhost:8085/media/54346_001.jpg', 11, 0),
(187, 'Huevos L ', 1.2, '1/2 Docena | 2,40 € / 1 Dc', 96, 'http://localhost:8085/media/7098411_001.jpg', 12, 0),
(188, 'Huevos M', 2, '2,00 € / 1 Dc', 96, 'http://localhost:8085/media/7098382_001.jpg', 12, 0),
(189, 'Leche Entera', 0.91, 'Brik 1 L | 0,91 € / L', 100, 'http://localhost:8085/media/7080596_001.jpg', 12, 0),
(190, 'Leche Semidesnatada', 0.83, 'Brik 1 L | 0,83 € / L', 82, 'http://localhost:8085/media/7080604_001.jpg', 12, 0),
(191, 'Leche Semidesnatada', 1.05, 'Brik 1 L | 1,05 € / L', 63, 'http://localhost:8085/media/735399_001.jpg', 12, 0),
(192, 'Leche Desnatada', 1, 'Brik 1 L | 1,00 € / L', 56, 'http://localhost:8085/media/735340_001.jpg', 12, 0),
(193, 'Yogur Sabor Fresa', 1, 'Pack de 4 Unidades 4 x 120 Gr | 2,08 € / Kg', 94, 'http://localhost:8085/media/204420.jpg', 12, 0),
(194, 'Yogur Sabor Limón', 1, 'Pack de 4 Unidades 4 x 120 Gr | 2,08 € / Kg', 59, 'http://localhost:8085/media/204230.jpg', 12, 0),
(195, 'Leche Desnatada ', 1.69, 'Botella 1,5 L | 1,13 € / L)', 62, 'http://localhost:8085/media/203927_001.jpg', 12, 0),
(196, 'Kéfir ', 2.19, 'Pack 6 Unidades 6 x 100 Gr | 3,65 € / Kg', 78, 'http://localhost:8085/media/7343283_001.jpg', 12, 0),
(197, 'Yogur Griego Azucarado', 5.67, 'Paquete 4 x 110 Gr | 5,43 € / Kg', 36, 'http://localhost:8085/media/7303527_001.jpg', 12, 0),
(198, 'Bebida de Avena', 1.45, 'Brik 1 L | 1,45 € / L', 55, 'http://localhost:8085/media/7180797_001.jpg', 12, 0),
(199, 'Bebida de Soja', 1.49, 'Brik 1 L | 1,49 € / L', 61, 'http://localhost:8085/media/7113033_001.jpg', 12, 0),
(200, 'Activia Natural Yogur', 1.92, 'Pack 4 Un 4 x 120 Gr | 4,14 € / Kg', 100, 'http://localhost:8085/media/224642_001.jpg', 12, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `apellido1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `apellido2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `direccion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(512) NOT NULL,
  `rol` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellido1`, `apellido2`, `email`, `telefono`, `direccion`, `username`, `password`, `rol`) VALUES
(1, 'Ana ', 'Pérez', 'Roca', 'anita@gmail.com', '632156987', 'Calle del Cerezo Nº 17', 'anita17', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 0),
(2, 'Monica', 'Alcañiz', 'Puig', 'monica@gmail.com', '642156657', 'Alameda', 'moni01', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(3, 'Valeria', 'Castillo', 'Flores', 'valcasfl0@gmail.com', '620722447', 'Calle del Marqués de Larios,Valladolid,47001', 'valasl0', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(4, 'Javier', 'Ramírez', 'Gómez', 'javramgo1@gmail.com', '622746639', 'Paseo de la Alameda,Navarra,31001', 'javamo1', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(5, 'Laura', 'Hernández', 'Castillo', 'lauherca2@gmail.com', '602265397', 'Paseo de Recoletos,Toledo,45001', 'lauera2', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(6, 'Tomás', 'Serrano', 'Díaz', 'tomserdi3@gmail.com', '614938407', 'Paseo de la Habana,La Rioja,26001', 'tomeri3', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(7, 'Emilio', 'Ramos', 'García', 'emiramga4@gmail.com', '666016880', 'Paseo de Recoletos,Toledo,45001', 'emiama4', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(8, 'Andrés', 'Rojas', 'Gómez', 'androjgo5@gmail.com', '606413159', 'Carrera de San Jerónimo,Salamanca,37001', 'andojo5', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(9, 'Emma', 'Ruiz', 'Gómez', 'emmruigo6@gmail.com', '649343038', 'Calle de la Paz,Segovia,40001', 'emmuio6', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(10, 'Elena', 'Pérez', 'Morales', 'elepermo7@gmail.com', '695361197', 'Calle del Príncipe,Murcia,30001', 'eleero7', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(11, 'Emilio', 'Pérez', 'Rojas', 'emiperro8@gmail.com', '605730667', 'Avenida de la Victoria,Islas Baleares,07001', 'emiero8', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(12, 'Martín', 'Molina', 'González', 'marmolgo9@gmail.com', '656721578', 'Avenida de la Libertad,Cáceres,10001', 'marolo9', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(13, 'Pablo', 'Rodríguez', 'López', 'pabrodlo10@gmail.com', '632387061', 'Calle de la Princesa,Lugo,27001', 'pabodo10', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(14, 'Carlos', 'Alvarez', 'Castillo', 'caralvca11@gmail.com', '630478290', 'Calle de la Paz,Palencia,34001', 'carlva11', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(15, 'Isabella', 'Rodríguez', 'Alvarez', 'isarodal12@gmail.com', '608212120', 'Calle del Arenal,Zaragoza,50001', 'isaodl12', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(16, 'María', 'Molina', 'Torres', 'marmolto13@gmail.com', '699933041', 'Avenida Menéndez Pelayo,Melilla,52001', 'marolo13', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(17, 'Santiago', 'Reyes', 'Suárez', 'sanreysu14@gmail.com', '674526280', 'Avenida de la Constitución,Teruel,44001', 'saneyu14', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(18, 'Santiago', 'Morales', 'Gómez', 'sanmorgo15@gmail.com', '647033854', 'Paseo de la Habana,Salamanca,37001', 'sanoro15', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(19, 'Adrián', 'Rodríguez', 'Hernández', 'adrrodhe16@gmail.com', '649198503', 'Paseo de la Explanada de España,Málaga,29001', 'adrode16', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(20, 'Andrés', 'Martínez', 'Morales', 'andmarmo17@gmail.com', '627800672', 'Paseo de la Explanada de España,Bizkaia,48001', 'andaro17', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(21, 'Diego', 'Jiménez', 'Blanco', 'diejimbl18@gmail.com', '670269971', 'Carrera de San Jerónimo,Coruña,15001', 'dieiml18', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(22, 'Alejandro', 'Alvarez', 'Pérez', 'alealvpe19@gmail.com', '671249320', 'Paseo de la Explanada de España,Coruña,15001', 'alelve19', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(23, 'Javier', 'Ramos', 'Díaz', 'javramdi20@gmail.com', '687151127', 'Avenida de la Libertad,Cantabria,39001', 'javami20', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(24, 'Catalina', 'Díaz', 'Delgado', 'catdiade21@gmail.com', '646044918', 'Paseo de la Habana,Valencia,46001', 'catiae21', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(25, 'Pablo', 'Ramírez', 'Ruiz', 'pabramru22@gmail.com', '616633031', 'Calle del Marqués de Larios,Ávila,05001', 'pabamu22', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(26, 'Alejandro', 'Ruiz', 'Alvarez', 'aleruial23@gmail.com', '627733946', 'Avenida de la Victoria,Madrid,28001', 'aleuil23', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(27, 'Tomás', 'Jiménez', 'Sánchez', 'tomjimsa24@gmail.com', '629922361', 'Paseo de la Independencia,Barcelona,08001', 'tomima24', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(28, 'Emilio', 'Martínez', 'García', 'emimarga25@gmail.com', '645934276', 'Avenida Gran Vía de Colón,Coruña,15001', 'emiara25', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(29, 'Julián', 'Martínez', 'Rodríguez', 'julmarro26@gmail.com', '660175808', 'Calle del Arenal,Badajoz,06001', 'jularo26', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(30, 'Laura', 'Delgado', 'Delgado', 'laudelde27@gmail.com', '631924224', 'Avenida Gran Vía de Colón,Cáceres,10001', 'lauele27', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(31, 'Sofía', 'Vázquez', 'Vázquez', 'sofvazva28@gmail.com', '648493784', 'Calle del Marqués de Larios,Barcelona,08001', 'sofaza28', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(32, 'Laura', 'Cruz', 'Delgado', 'laucrude29@gmail.com', '645843713', 'Calle Real,Jaén,23001', 'laurue29', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(33, 'Lucía', 'Delgado', 'Suárez', 'lucdelsu30@gmail.com', '605999963', 'Calle del Arenal,Cádiz,11001', 'lucelu30', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(34, 'Santiago', 'López', 'Gómez', 'sanlopgo31@gmail.com', '601363760', 'Avenida Menéndez Pelayo,Segovia,40001', 'sanopo31', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(35, 'Diego', 'Rodríguez', 'Santos', 'dierodsa32@gmail.com', '603481395', 'Paseo de la Explanada de España,Coruña,15001', 'dieoda32', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(36, 'Martín', 'Reyes', 'Suárez', 'marreysu33@gmail.com', '663578575', 'Paseo de la Habana,Albacete,02001', 'mareyu33', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(37, 'Adrián', 'Ruiz', 'Reyes', 'adrruire34@gmail.com', '693242367', 'Calle Real,Salamanca,37001', 'adruie34', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(38, 'Carlos', 'Ramos', 'Ramírez', 'carramra35@gmail.com', '692606956', 'Paseo de la Independencia,Segovia,40001', 'carama35', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(39, 'Emma', 'Cruz', 'Ramos', 'emmcrura36@gmail.com', '642267443', 'Calle de la Paz,Valencia,46001', 'emmrua36', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1),
(40, 'Emma', 'Santos', 'Flores', 'emmsanfl37@gmail.com', '614883783', 'Paseo de la Independencia,Cantabria,39001', 'emmanl37', 'dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoracion`
--

CREATE TABLE `valoracion` (
  `id` bigint NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `fecha` datetime NOT NULL,
  `mensaje` varchar(2048) NOT NULL,
  `id_usuario` bigint NOT NULL,
  `id_producto` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `valoracion`
--

INSERT INTO `valoracion` (`id`, `titulo`, `fecha`, `mensaje`, `id_usuario`, `id_producto`) VALUES
(51, 'Excelente calidad', '2024-06-12 11:39:15', 'Este aceite de oliva tiene un sabor excepcional y es perfecto para aderezar ensaladas o cocinar. La calidad es notable y el aroma es muy fresco. Muy recomendable.', 25, 1),
(52, 'Buen sabor, pero caro', '2024-06-12 11:39:15', 'El aceite de oliva tiene un sabor excelente, pero el precio es un poco alto. Aun así, vale la pena por su calidad.', 29, 2),
(53, 'Deliciosa', '2024-06-12 11:39:15', 'Esta salsa de trufa es deliciosa. Tiene un sabor muy auténtico y es perfecta para acompañar pastas y carnes. Muy recomendable.', 39, 13),
(54, 'Buen sabor, pero un poco fuerte', '2024-06-12 11:39:15', 'La salsa tiene un buen sabor, pero es un poco fuerte para mi gusto. Aun así, es una excelente opción para los amantes de la trufa.', 7, 13),
(55, 'Mi favorita', '2024-06-12 11:39:15', 'Esta salsa de setas es mi favorita. Tiene un sabor auténtico y la utilizo en muchas recetas. Definitivamente la volveré a comprar', 40, 15),
(56, 'Buen producto', '2024-06-12 11:39:15', 'Las nueces tienen buena calidad y sabor. Ideales para snacks y recetas. Le doy cuatro estrellas porque el precio es un poco elevado.', 7, 29),
(57, 'Deliciosos', '2024-06-12 11:39:16', 'Estos pistachos son deliciosos. Tienen un sabor muy fresco y son perfectos para snacks. Muy recomendables.', 35, 31),
(58, 'Sabor delicioso', '2024-06-12 11:39:16', 'Estas avellanas tienen un sabor delicioso. Son perfectas para snacks o para añadir a recetas. Muy recomendables.', 24, 34),
(59, 'Dulces y deliciosos', '2024-06-12 11:39:16', 'Estos dátiles son dulces y deliciosos. Tienen una textura suave y son perfectos para snacks o para añadir a recetas. Muy recomendables', 19, 35),
(60, 'Mis favoritos', '2024-06-12 11:39:16', 'Estos dátiles son mis favoritos. Tienen un sabor muy auténtico y una textura suave. Perfectos para cualquier ocasión.', 5, 35),
(61, 'Perfecto para repostería', '2024-06-12 11:39:18', 'Perfecto para repostería', 17, 8),
(62, 'Buena calidad, pero un poco amargo', '2024-06-12 11:39:18', 'El chocolate tiene muy buena calidad, pero es un poco más amargo de lo que prefiero. Aun así, es una excelente opción para los amantes del chocolate negro.', 32, 59),
(63, 'Correcto', '2024-06-12 11:39:18', 'El chocolate está bien, pero he probado otros con mejor sabor. ', 40, 57),
(64, 'Deliciosa y natural', '2024-06-12 11:39:18', 'Esta miel milflores es deliciosa y tiene un sabor muy natural. Es perfecta para endulzar bebidas y recetas. Muy recomendable.', 25, 65),
(65, 'Deliciosa y natural', '2024-06-12 11:39:18', 'Esta mermelada de fresa es deliciosa y tiene un sabor muy natural. Es perfecta para untar en pan, galletas o para usar en recetas. La textura es suave y el equilibrio de dulzura es perfecto. Definitivamente la recomendaría.', 32, 61),
(66, 'Sabor dulce y exquisito', '2024-06-12 11:39:18', 'Esta mermelada de melocotón tiene un sabor dulce y exquisito. Es perfecta para untar en pan, galletas o para usar en recetas. La textura es suave y el equilibrio de dulzura es perfecto. Definitivamente la recomendaría.', 9, 63),
(67, 'Buena opción, pero un poco dulce', '2024-06-12 11:39:18', 'La mermelada tiene buen sabor, pero es un poco más dulce de lo que prefiero. Aun así, es una opción excelente para los amantes del melocotón.', 26, 66),
(68, 'Sabor refinado', '2024-06-12 11:39:18', 'Este vermouth bianco tiene un sabor refinado y equilibrado. Es perfecto para cócteles o para disfrutar solo con hielo. Muy recomendable.', 4, 69),
(69, 'Sabor suave', '2024-06-12 11:39:18', 'Este whisky Cutty Sark tiene un sabor suave y equilibrado. Es perfecto para disfrutar solo o en cócteles. Muy recomendable.', 10, 73),
(70, 'Sabor afrutado', '2024-06-12 11:39:18', 'Este vino rosado D.O Rioja tiene un sabor afrutado y refrescante. Es perfecto para acompañar comidas o disfrutar solo. Muy recomendable.', 14, 80),
(71, 'Perfecto para el verano', '2024-06-12 11:46:01', 'Este vino rosado Fidencio es perfecto para el verano. Tiene un sabor fresco y afrutado que lo hace ideal para disfrutar en días calurosos. Muy recomendable.', 37, 81),
(72, 'Deliciosa y nutritiva', '2024-06-12 11:46:01', 'Esta crema de calabaza es deliciosa y muy nutritiva. Tiene una textura suave y un sabor increíble. Ideal para una cena ligera.', 13, 155),
(73, 'Delicioso y jugoso', '2024-06-12 11:46:01', 'Estos melocotones en almíbar son deliciosos y jugosos. Son perfectos para postres o para disfrutar solos. Muy recomendables.', 19, 161),
(74, 'Buena calidad', '2024-06-12 11:46:01', 'Los melocotones tienen buena calidad y sabor. Ideales para postres. Le doy cuatro estrellas porque el almíbar es un poco más dulce de lo que esperaba.', 40, 161),
(75, 'Buena calidad, pero se maduran rápido', '2024-06-12 11:46:01', 'Las bananas tienen muy buena calidad, pero se maduran un poco rápido. Aun así, el sabor es excelente.', 36, 166),
(76, 'Jugosas y dulces', '2024-06-12 11:46:01', 'Estas naranjas son jugosas y dulces. Perfectas para zumos o para comer frescas. Muy recomendables.', 29, 171),
(77, 'Buena opción, pero algunas son ácidas', '2024-06-12 11:46:02', 'Las naranjas tienen buen sabor, pero algunas son un poco ácidas para mi gusto. Aun así, son una excelente opción.', 14, 171),
(78, 'Buena calidad, pero algunos son duros', '2024-06-12 11:46:02', 'Los kiwis tienen muy buena calidad, pero algunos son un poco duros. Aun así, el sabor es excelente.', 14, 172),
(79, 'Excelente calidad', '2024-06-12 11:46:02', 'El solomillo de cerdo tiene una excelente calidad. Muy tierno y jugoso, ideal para cualquier tipo de receta. Lo recomiendo completamente.', 23, 85),
(80, 'Muy bueno, pero caro', '2024-06-12 11:46:02', 'El solomillo es muy bueno y sabroso, aunque el precio es un poco elevado. Aun así, vale la pena por la calidad.', 6, 142),
(81, 'Sabor auténtico', '2024-06-12 12:04:49', 'Este chorizo extra asturiano tiene un sabor auténtico y delicioso. Perfecto para tapas o para añadir a guisos. Muy recomendable.', 39, 88),
(82, 'Muy buen sabor', '2024-06-12 12:04:49', 'El chorizo tiene un sabor muy bueno, aunque podría tener un poco menos de grasa. Aun así, es una excelente opción.', 21, 88),
(83, 'Deliciosa y tierna', '2024-06-12 12:04:49', 'La carrillada de cerdo es deliciosa y muy tierna. Se deshace en la boca y es perfecta para guisos y estofados. Muy recomendable.', 23, 101),
(84, 'Fresca y saludable', '2024-06-12 12:04:50', 'La pechuga de pollo es fresca y muy saludable. Perfecta para cualquier tipo de receta, desde ensaladas hasta platos principales. Muy recomendable.', 25, 82),
(85, 'Correcta', '2024-06-12 12:04:50', 'La pechuga está bien, pero he probado otras con mejor sabor. Es una opción correcta, pero no la mejor que he comprado', 32, 82),
(86, 'Perfecto para paellas', '2024-06-12 12:04:50', 'Este arroz redondo es perfecto para paellas y otros platos de arroz. Tiene una buena textura y siempre queda en su punto. Muy recomendable.', 11, 37),
(87, 'Mi preferido', '2024-06-12 12:04:50', 'Este arroz es mi preferido. Siempre queda perfecto en todas mis recetas. Lo recomiendo totalmente.', 33, 40),
(88, 'Práctico y sabroso', '2024-06-12 12:04:50', 'Los garbanzos cocidos son muy prácticos y tienen un sabor excelente. Perfectos para ensaladas, guisos y más. Muy recomendables.', 20, 42),
(89, 'Muy convenientes', '2024-06-12 12:04:50', 'Estos garbanzos cocidos son muy convenientes para tener en la despensa. Siempre listos para usar y con un sabor delicioso. Los volveré a comprar.', 7, 43),
(90, 'Deliciosas', '2024-06-12 12:04:50', 'Estas aceitunas son deliciosas y tienen un sabor muy auténtico. Perfectas para aperitivos o para añadir a ensaladas. Muy recomendables.', 24, 27),
(91, 'Mis favoritas', '2024-06-12 12:04:52', 'Estas aceitunas son mis favoritas. Siempre frescas y con un sabor delicioso. Las volveré a comprar.', 34, 25),
(92, 'Crocantes y sabrosos', '2024-06-12 12:04:52', 'Estos pepinillos son crocantes y sabrosos. Perfectos para ensaladas, sándwiches o como snack. Muy recomendables.', 2, 28),
(93, 'Muy nutritivos', '2024-06-12 12:04:52', 'Estos copos de avena son muy nutritivos y tienen un sabor excelente. Perfectos para el desayuno o para añadir a recetas. Muy recomendables.', 17, 105),
(94, 'Delicioso y crujiente', '2024-06-12 12:04:52', 'Este muesli crunchy con chocolate es delicioso y muy crujiente. Perfecto para el desayuno o para un snack. Muy recomendable.', 9, 106),
(95, 'Muy buenos', '2024-06-12 12:04:52', 'Los copos son muy buenos y tienen un sabor excelente. Ideales para cualquier tipo de plato. Le doy cuatro estrellas porque a veces son un poco caros.', 37, 110),
(96, 'Delicioso y saludable', '2024-06-12 12:04:52', 'Este trigo integral con miel es delicioso y muy saludable. Perfecto para el desayuno o para un snack. Muy recomendable.', 11, 114),
(97, 'Nutritivo y delicioso', '2024-06-12 12:04:52', 'Este muesli de frutos secos es muy nutritivo y delicioso. Perfecto para el desayuno o para un snack saludable. Muy recomendable.', 15, 116),
(98, 'Deliciosa', '2024-06-12 12:04:52', 'La chistorra tiene un sabor increíble y es perfecta para cocinar a la parrilla o en guisos. Muy recomendable', 40, 90),
(99, 'Fresco y delicioso', '2024-06-12 12:04:53', 'El jamón cocido es fresco y tiene un sabor delicioso. Perfecto para sándwiches y ensaladas. Muy recomendable.', 9, 89),
(100, 'Muy buena', '2024-06-12 12:04:53', 'La longaniza tiene muy buen sabor y es muy jugosa. Le doy cuatro estrellas porque a veces es un poco seca.', 27, 93),
(101, 'Exquisito', '2024-06-12 12:11:54', 'El jamón Gran Reserva es exquisito y tiene un sabor increíble. Perfecto para cualquier ocasión especial. Muy recomendable.', 3, 96),
(102, 'Sabor intenso', '2024-06-12 12:11:54', 'El queso cheddar en lonchas tiene un sabor intenso y delicioso. Perfecto para sándwiches y hamburguesas. Muy recomendable.', 4, 120),
(103, 'Muy fresco', '2024-06-12 12:11:54', 'Los lomos de salmón son muy frescos y tienen un sabor excelente. Perfectos para cualquier receta de pescado. Muy recomendables.', 19, 147),
(104, 'Muy fresco', '2024-06-12 12:11:54', 'Los lomos de merluza son muy frescos y tienen un sabor excelente. Perfectos para cualquier receta de pescado. Muy recomendables.', 9, 125),
(105, 'Deliciosos', '2024-06-12 12:11:54', 'Los anillos a la romana son deliciosos y muy crujientes. Perfectos para cualquier ocasión. Muy recomendables.', 2, 126),
(106, 'Sorpresa saludable', '2024-06-12 12:11:54', 'Decidí probar el brócoli en un intento de llevar una dieta más saludable, y me sorprendió gratamente. Es versátil, fácil de preparar y tiene un sabor delicioso cuando se cocina adecuadamente. Le doy cuatro estrellas porque requiere un poco de tiempo extra para limpiarlo adecuadamente, pero definitivamente lo incluiré en mi dieta regularmente', 3, 135),
(107, 'Perfecta para guisos', '2024-06-12 12:11:54', 'La alubia blanca es ideal para preparar guisos reconfortantes. Se cocina fácilmente y tiene una textura suave. Además, absorbe muy bien los sabores de los ingredientes con los que se combina. ', 39, 44),
(108, 'Tierna y sabrosa', '2024-06-12 12:11:54', 'La carrillada de cerdo es una delicia culinaria. Tiene una textura tierna y un sabor delicioso que se deshace en la boca. Es perfecta para cocinar a fuego lento en estofados o guisos. Cinco estrellas sin duda.', 32, 101),
(109, 'Perfecta para postres', '2024-06-12 12:11:54', 'La nata montada es el complemento perfecto para cualquier postre. Su textura suave y su sabor dulce realzan el sabor de cualquier dulce, desde tartas hasta frutas frescas. Cinco estrellas sin duda', 15, 131),
(110, 'Frescas y sabrosas', '2024-06-12 12:11:54', 'Las manzanas son frescas, jugosas y llenas de sabor. Son perfectas para comer solas como un refrigerio saludable o para usar en una variedad de recetas, desde ensaladas hasta pasteles. Cinco estrellas sin duda', 25, 169),
(111, 'Deliciosamente refrescante', '2024-06-12 10:21:59', 'El yogur sabor fresa es mi favorito absoluto. Tiene un sabor refrescante y natural a fresas maduras, y la textura cremosa lo hace aún más delicioso. Es perfecto para cualquier momento del día.', 2, 193);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbphsp3c2ybpud5hthw7u8t340` (`id_usuario`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `valoracion`
--
ALTER TABLE `valoracion`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carrito`
--
ALTER TABLE `carrito`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=248;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT de la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=365;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=110;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=201;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT de la tabla `valoracion`
--
ALTER TABLE `valoracion`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=112;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FKbphsp3c2ybpud5hthw7u8t340` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
