Commit - 72fff926:OneToMany Bidirectional Mapping where @ManyToOne is Owning side and @OneToMany is inverse side.
	OneToMany Bidirectional Mapping - in OneToManyMappingTestRunner we have saved both holdings(vikasH,ashokH) in userVikas but still in DB vikasH is mapped with userVikas and ashokH is mapped with userAshok.Why?
	Because we have used mappedBy property in @OneToMany, we are saying JPA that Holdings is the owning relation and owning relation have higher precedence.So whatever user we put in vikasH,ashokH object using vikasH.setUser(); that mapping is executed instead of mapping from userVikas.setHoldings(list);
	Commenting em.persist(vikasH); and em.persist(ashokH); will not save holdings as  Holdings is owning relation we have to save like this only .Remember user.setHoldings(list) does not do anything as User is not the owning relation in this mapping.
	In next mapping we will see the inconsistency of making @OneToMany as owning relation of mapping.
	what is mappedBy property in @OneToMany - This tells Hibernate that the other side of the association is responsible for managing the relationship, in other words the side where the entity represents the table that contains the foreign key value.Also without mappedBy JPA executes 2 sql queries of insert and update but with it, JPA executes only one insert query.

Commit - 0f561fb70551:Show power of Lazy Loading.Run Main class and look for 'failed to lazily initialize the collection'

Commit - :Showing Power of Second Level Cache with EhCache provider On Non-Query select statement like entityManager.find() or session.get();
	Enable Second level Cache by doing 2 things.Adding @Cachebale and @Cache on Entity to be cachebale and enabling secondLevel cache by adding 2 properties in app.properties file.
	To Use EhCache we have used EhCacheRegionFactory in app.properties.
	To set custom config of ehCache create ehcache.xml is resource folder from where spring by default reads all properties configuration. See comments if we want to change name of ehcache.xml to something else.
	To demostrate just run the application.We have set maxIdleTime for cache entity to 10sec so change it in Thread.sleep() in SecondLevelCacheRunner.verifySecondLevelCacheAcrossEntityManager()
	