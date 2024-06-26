package nz.co.test.transactions.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import nz.co.test.transactions.viewmodel.TransactionViewModel

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
    
    @Binds
    @IntoMap
    @ViewModelKey(TransactionViewModel::class)
    protected abstract fun transactionListViewModel(transactionListViewModel: TransactionViewModel): ViewModel
}