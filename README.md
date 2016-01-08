<h1>Sistema de Entregas Walmart</h1>
<h4>Introdução e Motivação</h4>
<p>Sistema para teste do walmart que consiste em calcular a rota mais viavel(menor valor de entrega) em termos de custo baseada em malhas logisticas. Com esse sistema poderemos maximizar os ganhos entregando mais gastando menos e essa é a minha motivação, poder otimizar um processo ja existente trazendo mais ganhos para a companhia fazendo uso de meus conhecimentos e ferramentas que tenho prazer em trabalhar, visto que também há uma oportunidade pessoal para que eu possa agregar mais uma area de negócio as minhas competencias.</p>
<br />
<h4>Tecnologias Utilizadas</h4>
<ul>
<li><a href="http://www.oracle.com/technetwork/java/javase/downloads/index.html?ssSourceSiteId=otnjp">Java Runtime Edition 7</a></li>
<li><a href="https://docs.jboss.org/author/display/AS71/Documentation">Jboss Application Server 7.1 Final</a></li>

<li><a href="http://www.oracle.com/technetwork/java/javaee/tech/javaee6technologies-1955512.html">Java Enterprise Edition v6</a></li>
	<ul>
		<li><a href="https://jax-rs-spec.java.net/">JAX-RS 2.0 RESTful</a></li>
		<li><a href="http://docs.oracle.com/javaee/6/tutorial/doc/giwhl.html">CDI</a></li>
		<li><a href="http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html">Java Persistence API (JPA) 2</a></li>
	</ul>
<li>Design Pattern MVC</li>
<li>Maven 3.2</li>
<li><a href="http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html"> Algoritmo de Dijkstra </a></li> 
</ul>
<br />
<h4>Arquitetura</h4>
<br/>
Escolhi implementar o Java EE por ser um padrão que nos ajuda a implementar facilmente os requisitos funcionais com mais agilidade e segurança e tambpem por nos oferecer padros para implementações dos requisitos nao funcionais como persistência em banco de dados, transação, acesso remoto, web services, gerenciamento de threads, gerenciamento de conexões HTTP entre outros.
<br/><br/>
A aplicação foi dividida em diferentes camadas lógicas com papéis bem definidos e fracamente acopladas, onde cada uma depende estritamente das camadas inferiores, maximizando assim a manutenibilidade do código. Além disso, a utilização do CDI para injeção de dependência (DI) aumenta a flexibilidade. 
<br/><br/>
Os webservices do sistema foram expostos como APIs REST através do framework JAX-RS 2.0 RESTFul como forma de promover sua interoperabilidade entre sistemas. 
<br/><br/>
A base de dados que esta sendo usada é a H2 base in memory para que possamos rodar em fase de testes, mas para consumo em produção a troca é facilmente feita (1 linha de alteração no fonte) para a base de dados de preferencia do cliente graças ao JPA.
<br/><br/>
<h4>Instalação e deploy da aplicação</h4>
- <a href="http://jbossas.jboss.org/downloads/">Faça o download do JBoss AS 7.1.1.Final</a>
- <a href="https://docs.google.com/uc?authuser=0&id=0Bzq5cI-NNm3fYUxwc3pKbUcxcWc&export=download">Faça o download do WalmartLogistic-ear-0.0.1.ear (pacote) do Projeto</a>
- Copie o arquivo WalmartLogistic-ear-0.0.1.ear baixado no diretório $JBOSS_HOME/standalone/deployments
- Inicialize o servidor através do arquivo $JBOSS_HOME/bin/standalone.bat
- Após realizar os passos anterios com suceso os nossos webservices estão prontos para serem consumidos.
<br/><br/>
<h4>Prática</h4>
Temos 2 serviços do tipo REST:

<ul>
	<li>1- Inserção e Listagem de malhas</li>
		<ul>
			<li>Inserção de Malha</li>
				<ul>
					<li>Metodo: POST</li>
					<li>http://localhost:8080/WalmartLogistic-web/network</li>
					<li><pre>{ "name" : "SP", "segments" : [ { "origin" : "A", "destination" : "B", "distance" : "10" }, { "origin" : "B", "destination" : "D", "distance" : "15" }, { "origin" : "A", "destination" : "C", "distance" : "20" }, { "origin" : "C", "destination" : "D", "distance" : "30" }, { "origin" : "B", "destination" : "E", "distance" : "50" }, { "origin" : "D", "destination" : "E", "distance" : "30" } ] }</pre></li>
				</ul>
		
			<li>Exibir todos os Mapas cadastrados</li>
		 		<ul>
					<li>Metodo: GET</li>
					<li><a href="http://localhost:8080/WalmartLogistic-web/network">http://localhost:8080/WalmartLogistic-web/network</a></li>
				</ul>
			</li>
		</ul>
	<li>2- Consultar menor valor de entrega por caminho</li>
		<ul>
			<li>Metodo: GET</li>
			<li>
			<a href="http://localhost:8080/WalmartLogistic-web/calcBestCostDelivery?mapName=SP&origin=A&destination=D&autonomy=10&fuel=2.50">http://localhost:8080/WalmartLogistic-web/calcBestCostDelivery?mapName=SP&origin=A&destination=D&autonomy=10&fuel=2.50</a>
			</li>
			<li><pre>{"route":["A","B","D"],"cost":6.25}</pre>

</ul>

<br/>
<br/>
<br/>







