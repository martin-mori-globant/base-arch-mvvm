package com.example.basemvvmexample.data.mappers

import com.example.basemvvmexample.data.local.BreedRoom
import com.example.basemvvmexample.data.model.Breed

class BreedMapperToLocal : BaseMapper<BreedRoom, Breed> {
    override fun transform(type: BreedRoom): Breed {
        return Breed()
    }

    override fun transformToRepository(type: Breed): BreedRoom {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
