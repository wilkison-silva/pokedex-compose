package br.com.pokedex.android.di

import br.com.pokedex.android.BuildConfig
import br.com.pokedex.android.data.repository.PokemonRepositoryImpl
import br.com.pokedex.android.data.service.PokedexService
import br.com.pokedex.android.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


private const val URL_BASE = "https://pokeapi.co/api/v2/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_BASE)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient() : OkHttpClient {
        val client = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            client.addInterceptor(logging)
        }
        return client.build()
    }

    @Provides
    @Singleton
    fun providesPokedexService(retrofit: Retrofit) : PokedexService {
        return retrofit.create(PokedexService::class.java)
    }

    @Provides
    @Singleton
    fun providesPokemonRepository(pokedexService: PokedexService) : PokemonRepository {
        return PokemonRepositoryImpl(pokedexService)
    }

}