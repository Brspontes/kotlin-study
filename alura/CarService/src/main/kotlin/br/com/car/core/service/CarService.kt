package br.com.car.core.service

import br.com.car.adapters.http.CarHttpService
import br.com.car.core.converter.CarHttpToModelConverter
import br.com.car.domain.model.Car
import br.com.car.domain.ports.CarRepository
import br.com.car.domain.ports.CarService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
internal class CarService(
    private val carRepository: CarRepository,
    private val carHttpService: CarHttpService
) : CarService {

    @Cacheable(cacheNames = ["Cars"], key = "#root.method.name")
    override fun list(model: String?) =
        model?.let {
            carRepository.listByModel(it)
        } ?: carRepository.listAll()

    @CacheEvict(cacheNames = ["Cars"], allEntries = true)
    override fun save(car: Car) =
        car.isEligibleToUber().let { carRepository.save(it) }

    @CacheEvict(cacheNames = ["Cars"], allEntries = true)
    override fun update(car: Car, id: Long) = carRepository.update(car, id)

    override fun findById(id: Long): Car {
        return carRepository.findById(id) ?: throw RuntimeException()
    }

    override fun listByInventory(model: String): List<Car>? {
        val result = carHttpService.getByModel(model).execute().body()?.let {it ->
            CarHttpToModelConverter.toModel(it)
        }

        return result
    }
}
